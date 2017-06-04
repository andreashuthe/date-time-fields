package org.vaadin.demo;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;

public class DateTimeDemoBean {
    private DateTime dateTime = new DateTime();
    private Interval interval = new Interval(new DateTime(), new DateTime().plusDays(66));
    private LocalTime localTime = new LocalTime();
    
    public DateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
    public Interval getInterval() {
        return interval;
    }
    public void setInterval(Interval interval) {
        this.interval = interval;
    }
    public LocalTime getLocalTime() {
        return localTime;
    }
    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    
}
