/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package javax.portlet.tck.portlets;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import static java.util.logging.Logger.*;
import javax.xml.namespace.QName;
import javax.portlet.*;
import javax.portlet.filter.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.portlet.tck.beans.*;
import javax.portlet.tck.constants.*;
import static javax.portlet.tck.beans.JSR286DispatcherReqRespTestCaseDetails.*;
import static javax.portlet.tck.constants.Constants.*;
import static javax.portlet.PortletSession.*;
import static javax.portlet.ResourceURL.*;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 * This is the main portlet for the test cases. If the test cases call for events, this portlet
 * will initiate the events, but not process them. The processing is done in the companion 
 * portlet DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_event
 *
 */
public class DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse implements Portlet, ResourceServingPortlet {
   private static final String LOG_CLASS = 
         DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse.class.getName();
   private final Logger LOGGER = Logger.getLogger(LOG_CLASS);
   
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet processAction entry");

      portletResp.setRenderParameters(portletReq.getParameterMap());
      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      StringWriter writer = new StringWriter();

      String target = JSP_PREFIX + "DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse" + JSP_SUFFIX + QUERY_STRING;
      PortletRequestDispatcher rd = portletConfig.getPortletContext()
            .getRequestDispatcher(target);
      rd.forward(portletReq, portletResp);
   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet serveResource entry");

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet render entry");

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

      PortletSession ps = portletReq.getPortletSession();
      String msg = (String) ps.getAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse", APPLICATION_SCOPE);
      if (msg != null) {
         writer.write("<p>" + msg + "</p><br/>\n");
         ps.removeAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse", APPLICATION_SCOPE);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_containsHeader */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.containsHeader must return false"         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_containsHeader", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeRedirectURL1 */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.encodeRedirectURL must return null"       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeRedirectURL1", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeRedirectUrl */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.encodeRedirectUrl must return null"       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeRedirectUrl", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeURL1 */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.encodeURL must provide the same           */
      /* functionality as ActionResponse.encodeURL"                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeURL1", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeUrl */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.encodeUrl must provide the same           */
      /* functionality as ActionResponse.encodeURL"                           */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_encodeUrl", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getBufferSize */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.getBufferSize must return 0"              */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getBufferSize", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getCharacterEncoding */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.getCharacterEncoding must return null"    */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getCharacterEncoding", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getContentType */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.getContentType must return null"          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getContentType", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getLocale */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.getLocale must return null"               */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_getLocale", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_isCommitted */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.isCommitted must return false"            */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_isCommitted", aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_sendRedirect */
      /* Details: "In a target jsp of a forward in the Action phase, the      */
      /* method HttpServletResponse.sendRedirect must provide the same        */
      /* functionality as ActionResponse.sendRedirect"                        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton("V2DispatcherReqRespTests1_SPEC2_19_ForwardJSPActionResponse_sendRedirect", aurl);
         tb.writeTo(writer);
      }

   }

}
