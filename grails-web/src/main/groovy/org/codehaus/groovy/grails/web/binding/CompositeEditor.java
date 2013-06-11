/*
 * Copyright 2011 GoPivotal, Inc. All Rights Reserved
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
package org.codehaus.groovy.grails.web.binding;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Graeme Rocher
 * @since 2.0
 */
public class CompositeEditor extends PropertyEditorSupport {

    List<PropertyEditor> propertyEditors = new ArrayList<PropertyEditor>();

    public CompositeEditor(PropertyEditor...editors) {
        propertyEditors.addAll(Arrays.asList(editors));
    }

    @Override
    public void setAsText(String s) throws IllegalArgumentException {
        IllegalArgumentException first = null;
        boolean converted = false;
        for (PropertyEditor propertyEditor : propertyEditors) {
            try {
                propertyEditor.setAsText(s);
                converted = true;
                break;
            } catch (IllegalArgumentException e) {
                if (first == null) first = e;
            }
        }

        if (!converted && first != null) {
            throw first;
        }
    }

    public List<PropertyEditor> getPropertyEditors() {
        return propertyEditors;
    }
}
