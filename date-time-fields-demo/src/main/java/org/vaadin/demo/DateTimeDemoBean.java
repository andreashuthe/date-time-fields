package org.vaadin.demo;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;

public class DateTimeDemoBean {
    @Getter @Setter private DateTime dateTime = new DateTime();
    @Getter @Setter private Interval interval = new Interval(new DateTime(), new DateTime().plusDays(66));
    @Getter @Setter private LocalTime localTime = new LocalTime();
    @Getter @Setter private DateTime dateTimeAndLocalTime = new DateTime();
    
}
