/*
 * Copyright 2003,2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pluto.optional.db.preferences;

import org.apache.pluto.core.PortletPreference;
import org.apache.pluto.core.impl.PortletPreferenceImpl;
import org.apache.pluto.optional.db.common.AbstractPreparedDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Dao for preference persistance.
 *
 * @author <a href="mailto:cdoremus@apache.org">Craig Doremus</a>:
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>:
 * @version 1.0
 * @since Nov 23, 2005
 */
class PreferencesDao extends AbstractPreparedDao {

    private static final Log LOG =  LogFactory.getLog(PreferencesDao.class);

    PreferencesDao(DataSource dataSource) {
        super(dataSource);
    }

    PortletPreference[] getPreferences(String context,
                                       String portletName,
                                       String authUser)
    throws SQLException {
        if(LOG.isDebugEnabled()) {
            LOG.debug("Retrieving preferences for: "+context+" / "+portletName+" / "+authUser);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT pr.preference_id, pr.preference_name, pr.read_only, pv.preference_value")
          .append("  FROM preference_value pv, preference pr, portlet plt, portlet_app pa ")
          .append(" WHERE pa.app_context = ").append(fmt(context))
          .append("   AND plt.portlet_app_id = pa.portlet_app_id ")
          .append("   AND plt.portlet_name = ").append(fmt(portletName))
          .append("   AND pr.portlet_id = plt.portlet_id ")
          .append("   AND pr.auth_user = ").append(fmt(authUser))
          .append("   AND pv.preference_id = pr.preference_id");

        if(LOG.isDebugEnabled()) {
            LOG.debug("Executing SQL to retrieve preferences: \n"+sb.toString());
        }

        List list = super.doList(sb.toString());
        PortletPreferenceContainer container = new PortletPreferenceContainer();
        container.add(list);
        return container.createPreferences();
    }

    private static final String TEST_PR_SQL =
       "SELECT count(*)" +
       "  FROM preference pr, portlet p, portlet_app pa" +
       " WHERE pa.app_context = ? "+
       "   AND p.portlet_app_id = pa.portlet_app_id "+
       "   AND p.portlet_name = ? "+
       "   AND pr.portlet_id = p.portlet_id "+
       "   AND pr.preference_name = ?";


    private String createPreferenceSql(String context, String portletName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO preference (portlet_id, preference_name, auth_user, read_only) ")
                .append("     VALUES (")
                .append("             (SELECT portlet_id")
                .append("                FROM portlet plt, portlet_app pa ")
                .append("               WHERE pa.app_context = ").append(fmt(context))
                .append("                 AND plt.portlet_app_id = pa.portlet_app_id ")
                .append("                 AND plt.portlet_name = ").append(fmt(portletName))
                .append("             ), ?, ?, ?)");
        return sb.toString();
    }

    private String createPreferenceUpdateSql(String readOnly) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE preference ")
          .append("     SET read_only = ").append(fmt(readOnly))
          .append("     , mod_date = current_timestamp ")
          .append("     WHERE preference_id = " )
          .append("             (SELECT preference_id " )
          .append("                FROM preference pr, portlet plt, portlet_app pa ")
          .append("               WHERE pa.app_context = ? ")
          .append("                 AND plt.portlet_app_id = pa.portlet_app_id ")
          .append("                 AND plt.portlet_name = ? ")
          .append("                 AND pr.portlet_id = plt.portlet_id ")
          .append("                 AND pr.preference_name = ?")
          .append(             ")");
        return sb.toString();
    }
    
    private String createPreferenceValueInsertSql() {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO preference_value (preference_id, preference_value) ")
          .append("     VALUES ( " )
          .append("             (SELECT preference_id " )
          .append("                FROM preference pr, portlet plt, portlet_app pa ")
          .append("               WHERE pa.app_context = ? ")
          .append("                 AND plt.portlet_app_id = pa.portlet_app_id ")
          .append("                 AND plt.portlet_name = ? ")
          .append("                 AND pr.portlet_id = plt.portlet_id ")
          .append("                 AND pr.preference_name = ?")
          .append(        "), ?)");
        return sb.toString();
    }

    private String createPreferenceValueDeleteSql() {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM preference_value ")
          .append("     WHERE preference_id = " )
          .append("             (SELECT preference_id " )
          .append("                FROM preference pr, portlet plt, portlet_app pa ")
          .append("               WHERE pa.app_context = ? ")
          .append("                 AND plt.portlet_app_id = pa.portlet_app_id ")
          .append("                 AND plt.portlet_name = ? ")
          .append("                 AND pr.portlet_id = plt.portlet_id ")
          .append("                 AND pr.preference_name = ?")
          .append(             ")");
        return sb.toString();
    }
    
   void storePreferences(String context,
                          String portletName,
                          String authUser,
                          PortletPreference[] preferences)
    throws SQLException {

       boolean autoCommit = false;

       Connection conn = null;

       PreparedStatement testStmt = null;

       PreparedStatement stmt = null;
       PreparedStatement updateStmt = null;
       PreparedStatement valueStmt = null;
       PreparedStatement valDelStmt = null;

       ResultSet rs = null;

       try {
           conn = getConnection();
           autoCommit = conn.getAutoCommit();
           conn.setAutoCommit(false);

           //tests to see if record exists in preference table
           testStmt = conn.prepareStatement(TEST_PR_SQL);
           stmt = conn.prepareStatement(createPreferenceSql(context, portletName));
           valueStmt = conn.prepareStatement(createPreferenceValueInsertSql());
           valDelStmt = conn.prepareStatement(createPreferenceValueDeleteSql());
           boolean insertNeeded = false;
           boolean updateNeeded = false;
           for(int i=0;i<preferences.length;i++) {

               testStmt.setString(1, context);
               testStmt.setString(2, portletName);
               testStmt.setString(3, preferences[i].getName());

               rs = testStmt.executeQuery();
               //insert/update preference table
               if(rs.next() && rs.getInt(1) < 1) {
            	   //insert new preference
            	   if (!insertNeeded) {
            		   insertNeeded = true;
            	   }
                   if(LOG.isDebugEnabled()) {
                       LOG.debug("Creating preference for user '"+authUser+"': "+context+"/"+portletName+"/"+preferences[i].getName());
                   }
                   stmt.setString(1, preferences[i].getName());
                   stmt.setString(2, authUser);
                   stmt.setString(3, preferences[i].isReadOnly()?"Y":"N");
                   stmt.addBatch();


               } else {
            	   //update preference
            	   if (!updateNeeded) {
            		   updateNeeded = true;
            	   }
                   if(LOG.isDebugEnabled()) {
                       LOG.debug("Updating preference for user '"+authUser+"': "+context+"/"+portletName+"/"+preferences[i].getName());
                   }
                   updateStmt = conn.prepareStatement(createPreferenceUpdateSql(preferences[i].isReadOnly()?"Y":"N"));
                   updateStmt.addBatch();
               }
               
               //delete + insert into preference_value table
               //delete old preference values
               valDelStmt.setString(1, context);
               valDelStmt.setString(2, portletName);
               valDelStmt.setString(3, preferences[i].getName());
               valDelStmt.addBatch();
               
               //insert new values
               String[] values = preferences[i].getValues();
               for(int j=0;j<values.length;j++) {
                   valueStmt.setString(1, context);
                   valueStmt.setString(2, portletName);
                   valueStmt.setString(3, preferences[i].getName());
                   valueStmt.setString(4, values[j]);
                   valueStmt.addBatch();
               }
               
           }
           //execute all batch updates
           if (insertNeeded) {
        	   stmt.executeBatch();
           }
           if (updateNeeded) {
        	   updateStmt.executeBatch();
           }
           valDelStmt.executeBatch();
           valueStmt.executeBatch();
           //commit
           conn.commit();
       }
       catch(SQLException e) {
           if(LOG.isErrorEnabled()) {
               LOG.error("Error saving preferences: ", e);
           }
           if (conn != null) {
        	   conn.rollback();
           }
           throw e;
       } 
       catch(Throwable e) {
           if(LOG.isErrorEnabled()) {
               LOG.error("Error saving preferences: ", e);
           }
           if (conn != null) {
        	   conn.rollback();
           }
           //Make sure the client knows about problem
           SQLException e1 = new SQLException();
           e1.initCause(e);
           throw e1;
       } 
       finally {
    	   cleanup(null, null, rs);
           cleanup(null, testStmt, null);
           cleanup(null, stmt, null);
           cleanup(null, updateStmt, null);
           cleanup(null, valDelStmt, null);
           conn.setAutoCommit(autoCommit);
           cleanup(conn, valueStmt, null);
       }
    }

    protected Object instantiate(ResultSet rs) throws SQLException {
        String name  = rs.getString("preference_name");
        String value = rs.getString("preference_value");
        boolean readOnly = rs.getString("read_only").equals("Y");
        return new PortletPreferenceImpl(name, new String[] { value }, readOnly);
    }

}