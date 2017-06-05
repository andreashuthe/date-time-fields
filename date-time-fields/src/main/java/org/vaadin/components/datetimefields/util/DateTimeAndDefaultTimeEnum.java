package org.vaadin.components.datetimefields.util;

/**
 * Created by andreas_h on 05.06.17.
 */
public enum DateTimeAndDefaultTimeEnum {

    START("00:00"),
    END("23:59"),
    DEFAULT(""),
    NOW(null);

    private final String timeValue;

    DateTimeAndDefaultTimeEnum (final String timeValue){
        this.timeValue = timeValue;
    }

    public final String getTimeValue(){
        return timeValue;
    }

    public static DateTimeAndDefaultTimeEnum getEnumFromValue(final String value){

        if (value == null){
            return DateTimeAndDefaultTimeEnum.NOW;
        } else {
            for (final DateTimeAndDefaultTimeEnum dateTimeAndDefaultTime : values()) {
                if (dateTimeAndDefaultTime.timeValue != null && dateTimeAndDefaultTime.timeValue.equals(value)){
                    return dateTimeAndDefaultTime;
                }
            }
        }
        return DateTimeAndDefaultTimeEnum.DEFAULT;
    }
}
