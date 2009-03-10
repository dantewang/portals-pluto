/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pluto.container.spi.optional;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.pluto.container.PortletContainerException;
import org.apache.pluto.container.spi.FilterManager;

/**
 * Service used to invoke portlets.
 */
public interface PortletInvokerService {
	
    public void action(PortletRequestContext ctx, ActionRequest req, ActionResponse res, FilterManager filterManager)
    	throws IOException, PortletException, PortletContainerException;
    
    public void event(PortletRequestContext ctx, EventRequest request, EventResponse response, FilterManager filterManager)
        throws IOException, PortletException, PortletContainerException;

    public void render(PortletRequestContext ctx, RenderRequest req, RenderResponse res, FilterManager filterManager)
        throws IOException, PortletException, PortletContainerException;
    
    public void serveResource(PortletRequestContext ctx, ResourceRequest req, ResourceResponse res, FilterManager filterManager)
        throws IOException, PortletException, PortletContainerException;

    public void load(PortletRequestContext ctx, PortletRequest req, PortletResponse res)
        throws IOException, PortletException, PortletContainerException;
    
    public void admin(PortletRequestContext ctx, PortletRequest req, PortletResponse res)
        throws IOException, PortletException, PortletContainerException;
}