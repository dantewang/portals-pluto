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

package javax.portlet.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.portlet.ActionURL;
import javax.portlet.CacheControl;
import javax.portlet.MimeResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderURL;
import javax.portlet.ResourceURL;

/**
 * <div class="changed_added_3_0"> The <code>MimeResponseWrapper</code> provides
 * a convenient implementation of the <code>MimeResponse</code> interface that
 * can be subclassed by developers. This class
 * implements the Wrapper or Decorator pattern. Methods default to calling
 * through to the wrapped response object. </div>
 * 
 * @since 3.0
 */
public class MimeResponseWrapper extends PortletResponseWrapper implements MimeResponse {

   /**
    * @param response
    * 
    * @since 3.0
    */
   public MimeResponseWrapper(MimeResponse response) {
      super(response);
   }

   /**
    * <div class="changed_added_3_0">
    * Return the wrapped response object.
    * </div>
    * 
    * @return the wrapped response
    * 
    * @since 3.0
    */
   public MimeResponse getResponse() {
      return (MimeResponse) super.getResponse();
   }

   /**
    * <div class="changed_added_3_0">
    * Sets the response object being wrapped.
    * </div>
    * 
    * @param response
    *           the response to set
    * @throws java.lang.IllegalArgumentException
    *            if the response is null.
    * 
    * @since 3.0
    */
   public void setResponse(MimeResponse response) {
      super.setResponse(response);
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call <code>flushBuffer()</code>
    * on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public void flushBuffer() throws IOException {
      ((MimeResponse)response).flushBuffer();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>getBufferSize()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public int getBufferSize() {
      return ((MimeResponse)response).getBufferSize();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>getCharacterEncoding()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public String getCharacterEncoding() {
      return ((MimeResponse)response).getCharacterEncoding();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>getContentType()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public String getContentType() {
      return ((MimeResponse)response).getContentType();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call <code>getLocale()</code> on
    * the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public Locale getLocale() {
      return ((MimeResponse)response).getLocale();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>getPortletOutputStream()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public OutputStream getPortletOutputStream() throws IOException {
      return ((MimeResponse)response).getPortletOutputStream();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call <code>getWriter()</code> on
    * the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public PrintWriter getWriter() throws IOException {
      return ((MimeResponse)response).getWriter();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call <code>isCommitted()</code>
    * on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public boolean isCommitted() {
      return ((MimeResponse)response).isCommitted();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call <code>reset()</code> on the
    * wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public void reset() {
      ((MimeResponse)response).reset();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call <code>resetBuffer()</code>
    * on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public void resetBuffer() {
      ((MimeResponse)response).resetBuffer();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>setBufferSize(size)</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public void setBufferSize(int size) {
      ((MimeResponse)response).setBufferSize(size);
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>getCacheControl()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public CacheControl getCacheControl() {
      return ((MimeResponse)response).getCacheControl();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>createActionURL()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public <T extends PortletURL & ActionURL> T createActionURL() throws IllegalStateException {
      return ((MimeResponse)response).createActionURL();
   }

   /**
    * <span class="changed_added_3_0">The default behavior of this method is to
    * call <code>createActionURL(ParameterCopyOption)</code> on the wrapped
    * response object.</span>
    * 
    * @since 3.0
    */
   public ActionURL createActionURL(Copy option) throws IllegalStateException {
      return ((MimeResponse)response).createActionURL(option);
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>createRenderURL()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public <T extends PortletURL & RenderURL> T createRenderURL() throws IllegalStateException {
      return ((MimeResponse)response).createRenderURL();
   }

   /**
    * <span class="changed_added_3_0">The default behavior of this method is to
    * call <code>createRenderURL(ParameterCopyOption)</code> on the wrapped
    * response object.</span>
    * 
    * @since 3.0
    */
   public RenderURL createRenderURL(Copy option) throws IllegalStateException {
      return ((MimeResponse)response).createRenderURL(option);
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>createResourceURL()</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public ResourceURL createResourceURL() throws IllegalStateException {
      return ((MimeResponse)response).createResourceURL();
   }

   /**
    * <div class="changed_added_3_0">
    * The default behavior of this method is to call
    * <code>setContentType(type)</code> on the wrapped response object.
    * </div>
    * 
    * @since 3.0
    */
   public void setContentType(String type) {
      ((MimeResponse)response).setContentType(type);
   }

}
