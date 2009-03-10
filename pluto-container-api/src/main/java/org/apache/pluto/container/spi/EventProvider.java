/*
 * Copyright 2006 The Apache Software Foundation.
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
package org.apache.pluto.container.spi;

import java.io.Serializable;

import javax.portlet.Event;
import javax.xml.namespace.QName;

	/**
	 *
	 * @author <a href="mailto:ferdy@informatik.uni-jena.de">Fred Thiele</a>
	 * @author <a href="mailto:chrisra@cs.uni-jena.de">Christian Raschka</a>
	 *
	 */

public interface EventProvider {

	public Event createEvent(QName name, Serializable value)
			throws IllegalArgumentException;
}