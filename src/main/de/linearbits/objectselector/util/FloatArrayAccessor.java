/*
 * ObjectSelector - Object selection library for Java
 * Copyright (C) 2013 - 2016 Fabian Prasser
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.linearbits.objectselector.util;

import de.linearbits.objectselector.datatypes.DataType;

/**
 * An accessor arrays of floats
 * 
 * @author Fabian Prasser
 */
public class FloatArrayAccessor extends PrimitiveArrayAccessor<float[]>{

    /**
     * Constructor without data types. Everything is treated as a string
     * 
     * @param header
     */
    public FloatArrayAccessor(String[] header) {
        super(header, DataType.NUMERIC);
    }

    /**
     * Constructor with data types
     * 
     * @param header
     * @param datatypes
     */
    public FloatArrayAccessor(String[] header, DataType<?>[] datatypes) {
        super(header, datatypes);
    }

    @Override
    public Object getValue(float[] object, String context) {
        return object[super.map.get(context)];
    }

}
