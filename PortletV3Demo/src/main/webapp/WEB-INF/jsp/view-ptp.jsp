<%--
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed  under the  License is distributed on an "AS IS" BASIS,
WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
implied.

See the License for the specific language governing permissions and
limitations under the License.
--%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0"  prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="static basic.portlet.Constants.*" %>

<portlet:defineObjects />

<%
   String setType = renderRequest.getRenderParameters().getValue(PARAM_SETTYPE);
   setType = (setType == null) ? PARAM_SETTYPE_VARRAY : setType;
   
   String remType = renderRequest.getRenderParameters().getValue(PARAM_REMTYPE);
   remType = (remType == null) ? PARAM_REMTYPE_SET : remType;
%>

<h3>Parameter Tester</h3><hr/>
<p>This portlet uses V3 APIs but does not use the portlet hub.</p>
<p>Enter parameter name and value and click 'send'.
To enter multiple values, separate values with a ',' character.
Entering 'null' as a value will cause the corresponding value in the values array to be set to null.
Entering 'null' by itself will cause the parameter to be set to null (removed).
Entering 'empty' by itself will set the parameter to an empty array.
Leaving the value field empty will set the parameter to an array containing a single empty string.
<p/>
<p><hr/></p>
<FORM id='<portlet:namespace/>-setParams' METHOD='POST' ACTION='<portlet:actionURL/>' enctype='application/x-www-form-urlencoded' accept-charset='UTF-8'>
   <table><tr><td align='left'>

   To set, use:
   </td><td>

   <input type='radio' name='<%=PARAM_SETTYPE%>' value='<%=PARAM_SETTYPE_VAL%>'
          <%=(setType.equals(PARAM_SETTYPE_VAL) ? "checked" : "")%> >Single value

   </td><td>
   <input type='radio' name='<%=PARAM_SETTYPE%>' value='<%=PARAM_SETTYPE_VARRAY%>'
          <%=(setType.equals(PARAM_SETTYPE_VARRAY) ? "checked" : "")%> >Value array

   </td></tr><tr><td>
   To remove, use:
   </td><td>

   <input type='radio' name='<%=PARAM_REMTYPE%>' value='<%=PARAM_REMTYPE_REM%>'
          <%=(remType.equals(PARAM_REMTYPE_REM) ? "checked" : "")%> >removePRP

   </td><td>
   <input type='radio' name='<%=PARAM_REMTYPE%>' value='<%=PARAM_REMTYPE_SET%>'
          <%=(remType.equals(PARAM_REMTYPE_SET) ? "checked" : "")%> >setRenderParameter

   </td></tr><tr><td>
      Parameter Name:
   </td><td colspan=2>
   <input name='<%=PARAM_NAME%>' type='text' value='' size='50' maxlength='100'>
   </td></tr><tr><td>
      Parameter Values:
   </td><td colspan=2>
   <input name='<%=PARAM_VALUES%>' type='text' value='' size='50' maxlength='100'>
   </td></tr><tr><td>
   <INPUT VALUE='send' CLASS='portlet-form-button' TYPE='submit'>
   </td></tr></table>
</FORM>

<p><hr/></p>
<%=renderRequest.getAttribute(ATTRIB_PARAMS) %>
<p><hr/></p>