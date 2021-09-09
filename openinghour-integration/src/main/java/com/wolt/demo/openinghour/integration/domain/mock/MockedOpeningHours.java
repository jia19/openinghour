package com.wolt.demo.openinghour.integration.domain.mock;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.wolt.demo.openinghour.domain.OpeningHourAction;

public class MockedOpeningHours {
    private List<OpeningHourAction> monday;
    private List<OpeningHourAction> tuesday;
    private List<OpeningHourAction> wednesday;
    private List<OpeningHourAction> thursday;
    private List<OpeningHourAction> friday;
    private List<OpeningHourAction> saturday;
    private List<OpeningHourAction> sunday;

    public MockedOpeningHours() {
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();
        saturday = new ArrayList<>();
        sunday = new ArrayList<>();
    }

    @Override
    public String toString() {
        // @formatter:off
        return new ToStringBuilder(this)
                .append("monday", monday)
                .append("tuesday", tuesday)
                .append("wednesday", wednesday)
                .append("thursday", thursday)
                .append("friday", thursday)
                .append("saturday", thursday)
                .append("sunday", thursday)
                .toString();
        // @formatter:on
    }

    public List<OpeningHourAction> getMonday() {
        return monday;
    }

    public void setMonday(List<OpeningHourAction> monday) {
        this.monday = monday;
    }

    public List<OpeningHourAction> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<OpeningHourAction> tuesday) {
        this.tuesday = tuesday;
    }

    public List<OpeningHourAction> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<OpeningHourAction> wednesday) {
        this.wednesday = wednesday;
    }

    public List<OpeningHourAction> getThursday() {
        return thursday;
    }

    public void setThursday(List<OpeningHourAction> thursday) {
        this.thursday = thursday;
    }

    public List<OpeningHourAction> getFriday() {
        return friday;
    }

    public void setFriday(List<OpeningHourAction> friday) {
        this.friday = friday;
    }

    public List<OpeningHourAction> getSaturday() {
        return saturday;
    }

    public void setSaturday(List<OpeningHourAction> saturday) {
        this.saturday = saturday;
    }

    public List<OpeningHourAction> getSunday() {
        return sunday;
    }

    public void setSunday(List<OpeningHourAction> sunday) {
        this.sunday = sunday;
    }
}
