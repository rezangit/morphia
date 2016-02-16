/*
 * Copyright (c) 2008-2016 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.morphia.converters;

import org.mongodb.morphia.mapping.MappedField;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

/**
 * Provides a converter for {@link Year} converting it to its numeric form.
 */
public class YearConverter extends TypeConverter implements SimpleValueConverter {

    /**
     * Creates the Converter.
     */
    public YearConverter() {
        super(Year.class);
    }

    @Override
    public Object decode(final Class<?> targetClass, final Object val, final MappedField optionalExtraInfo) {
        if (val == null) {
            return null;
        }

        if (val instanceof Year) {
            return val;
        }

        if (val instanceof Date) {
            final Date date = (Date) val;
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            return Year.of(cal.get(Calendar.YEAR));
        }

        if (val instanceof Number) {
            return Year.of(((Number) val).intValue());
        }

        if (val instanceof String) {
            return Year.of(Integer.parseInt((String) val));
        }

        throw new IllegalArgumentException("Can't convert to LocalDateTime from " + val);
    }

    @Override
    public Object encode(final Object value, final MappedField optionalExtraInfo) {
        return ((Year) value).getValue();

    }

}
