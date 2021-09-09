package com.wolt.demo.openinghour.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Restaurant opening hours object.
 *
 */
@Schema(description = "Restaurant opening hours")
public class RestaurantOpeningHours {

    @Schema(description = "Opening hours")
    private List<OpeningHour> openingHours;

    public RestaurantOpeningHours() {
        openingHours = new ArrayList<>();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    public List<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<OpeningHour> convertedOpeningHours) {
        this.openingHours = convertedOpeningHours;
    }

}
