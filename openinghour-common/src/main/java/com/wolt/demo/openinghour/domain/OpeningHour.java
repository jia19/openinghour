package com.wolt.demo.openinghour.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Opening hours")
public class OpeningHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Schema(description = "Week day")
    private WeekDay weekday;

    @Schema(description = "Open or close")
    private List<OpeningHourAction> actions;

    public OpeningHour() {
        actions = new ArrayList<>();
    }

    public OpeningHour(WeekDay weekday, List<OpeningHourAction> list) {
        this.weekday = weekday;
        this.actions = list;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    public WeekDay getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekDay weekday) {
        this.weekday = weekday;
    }

    public List<OpeningHourAction> getActions() {
        return actions;
    }

    public void setActions(List<OpeningHourAction> actions) {
        this.actions = actions;
    }
}
