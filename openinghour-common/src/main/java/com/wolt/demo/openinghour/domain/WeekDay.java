package com.wolt.demo.openinghour.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WeekDay {
    // @formatter:off
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");
    // @formatter:on

    private String jsonValue;

    private WeekDay(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String jsonValue() {
        return jsonValue;
    }
}
