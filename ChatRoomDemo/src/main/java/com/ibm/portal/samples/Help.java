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


package com.ibm.portal.samples;

import javax.portlet.annotations.PortletConfiguration;
import javax.portlet.annotations.RenderMethod;
import javax.portlet.annotations.Supports;

/**
 * Displays help information
 * 
 * @author Scott Nicklous
 *
 */
@PortletConfiguration(portletName="BeanPortletDemo",
      supports = {@Supports(mimeType = "text/html", portletModes = {"view", "help"})})
public class Help {

   /**
    * Render method using automatic include for 'help' portlet mode
    */
   @RenderMethod(portletNames ="BeanPortletDemo", 
                 include = "/WEB-INF/jsp/help.jsp", 
                 ordinal = 100, 
                 portletMode = "help")
   public void getImageInclude() {}

}