/*
 * Copyright 2004-2005 GoPivotal, Inc. All Rights Reserved.
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
package org.codehaus.groovy.grails.web.servlet

import org.codehaus.groovy.grails.plugins.web.AbstractGrailsPluginTests
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpSession

/**
 * Tests for the render method
 *
 * @author Graeme Rocher
 */
class GrailsHttpSessionTests extends AbstractGrailsPluginTests {

    void onSetUp() {
        pluginsToLoad << gcl.loadClass("org.codehaus.groovy.grails.plugins.CoreGrailsPlugin")
        pluginsToLoad << gcl.loadClass("org.codehaus.groovy.grails.plugins.web.ServletsGrailsPlugin")
    }

    void testSetAttribute() {
        def grailsSession = new GrailsHttpSession(new MockHttpServletRequest())
        grailsSession.myAttribute = "blah"
        assertEquals "blah", grailsSession.myAttribute
    }

    void testRemoveAttribute() {
        def mock = new MockHttpSession()
        def grailsSession = new GrailsHttpSession(new MockHttpServletRequest())

        grailsSession.myAttribute = "blah"
        assertEquals "blah", grailsSession.myAttribute

        grailsSession.removeAttribute("myAttribute")
        assertNull(grailsSession.myAttribute)
    }
}
