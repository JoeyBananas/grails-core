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
package org.codehaus.groovy.grails.web.servlet.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

/**
 * Handles URL mapping for Grails.
 *
 * @author Graeme Rocher
 */
public class GrailsUrlHandlerMapping extends SimpleUrlHandlerMapping {

    public static final String APPLICATION_CONTEXT_ID = "handlerMapping";
    public static final String APPLICATION_CONTEXT_TARGET_SOURCE = "handlerMappingTargetSource";

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.handler.AbstractUrlHandlerMapping#getHandlerInternal(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        String appPath = urlPathHelper.getPathWithinApplication(request);
        if (logger.isDebugEnabled()) {
            logger.debug("Looking up handler for [" + appPath + "]");
        }
        return lookupHandler(appPath, request);
    }
}
