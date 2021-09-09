package com.wolt.demo.openinghour.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActionType {
    OPEN("open"),
    CLOSE("close");

    private String jsonValue;

    private ActionType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String jsonValue() {
        return jsonValue;
    }
}
