package com.wolt.demo.openinghour.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Opening hour action")
public class OpeningHourAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(required = true)
    private ActionType type;

    @Schema(required = true,
            example = "32400",
            description = "opening / closing time as UNIX time (1.1.1970 as a date).")
    @Min(value = 0)
    @Max(value = 86399)
    private int value;

    public OpeningHourAction() {
        super();
    }

    public OpeningHourAction(ActionType type, @Min(0) @Max(86399) int value) {
        super();
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
