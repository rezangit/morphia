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

import org.junit.Test;

import java.text.ParseException;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class YearConverterTest extends ConverterTest {
    public YearConverterTest() {
        super(new YearConverter());
    }

    @Test
    public void testConversion() throws ParseException {
        compare(Year.class, Year.now());
        final Year now = Year.now();
        compare(Year.class, new Date(), now);
        compare(Year.class, Calendar.getInstance().get(Calendar.YEAR), now);
        compare(Year.class, Calendar.getInstance().get(Calendar.YEAR) + "", now);

        Year date = Year.of(2016);
        Year ldt = Year.parse("2016/Jan/20 14:30:15", DateTimeFormatter.ofPattern("yyyy/MMM/dd HH:mm:ss"));
        compare(Year.class, date, ldt);
    }

}
