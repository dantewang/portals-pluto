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
import static javax.portlet.tck.beans.JSR286ApiTestCaseDetails.*;
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
 * portlet PortletTests_PortletConfig_ApiRender2_event
 *
 */
public class PortletTests_PortletConfig_ApiRender2 implements Portlet, ResourceServingPortlet {
   private static final String LOG_CLASS = 
         PortletTests_PortletConfig_ApiRender2.class.getName();
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

      JSR286ApiTestCaseDetails tcd = new JSR286ApiTestCaseDetails();

      // Create result objects for the tests

      ClassChecker cc = new ClassChecker(portletConfig.getClass());

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getInitParameter2  */
      /* Details: "Method getInitParameter(String): Returns null if the       */
      /* specified initialization parameter does not exist"                   */
      TestResult tr0 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETINITPARAMETER2);
      /* TODO: implement test */
      tr0.appendTcDetail("Not implemented.");
      tr0.writeTo(writer);

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getInitParameterNames2 */
      /* Details: "Method getInitParameterNames(): Returns an empty           */
      /* Enumeration if there are no initialization parameters available "    */
      TestResult tr1 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETINITPARAMETERNAMES2);
      /* TODO: implement test */
      tr1.appendTcDetail("Not implemented.");
      tr1.writeTo(writer);

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getPublicRenderParameterNames2 */
      /* Details: "Method getPublicRenderParameterNames(): Returns an empty   */
      /* Enumeration if there are no public render parameters are defined "   */
      TestResult tr2 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETPUBLICRENDERPARAMETERNAMES2);
      /* TODO: implement test */
      tr2.appendTcDetail("Not implemented.");
      tr2.writeTo(writer);

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getDefaultNamespace2 */
      /* Details: "Method getDefaultNamespace(): Returns                      */
      /* XMLConstants.NULL_NS_URI if no default namespace is defined in the   */
      /* deployment descriptor "                                              */
      TestResult tr3 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETDEFAULTNAMESPACE2);
      /* TODO: implement test */
      tr3.appendTcDetail("Not implemented.");
      tr3.writeTo(writer);

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getPublishingEventQNames2 */
      /* Details: "Method getPublishingEventQNames(): Returns an empty        */
      /* Enumeration if there are no processing events are defined"           */
      TestResult tr4 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETPUBLISHINGEVENTQNAMES2);
      /* TODO: implement test */
      tr4.appendTcDetail("Not implemented.");
      tr4.writeTo(writer);

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getProcessingEventQNames2 */
      /* Details: "Method getProcessingEventQNames(): Returns an empty        */
      /* Enumeration if there are no processing events are defined"           */
      TestResult tr5 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETPROCESSINGEVENTQNAMES2);
      /* TODO: implement test */
      tr5.appendTcDetail("Not implemented.");
      tr5.writeTo(writer);

      /* TestCase: V2PortletTests_PortletConfig_ApiRender2_getSupportedLocales2 */
      /* Details: "Method getSupportedLocales(): Returns an empty             */
      /* Enumeration if there are no supported locales are defined"           */
      TestResult tr6 = tcd.getTestResultFailed(V2PORTLETTESTS_PORTLETCONFIG_APIRENDER2_GETSUPPORTEDLOCALES2);
      /* TODO: implement test */
      tr6.appendTcDetail("Not implemented.");
      tr6.writeTo(writer);

   }

}