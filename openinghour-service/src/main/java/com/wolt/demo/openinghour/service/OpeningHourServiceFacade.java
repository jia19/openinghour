package com.wolt.demo.openinghour.service;

import com.wolt.demo.openinghour.domain.RestaurantOpeningHours;

public interface OpeningHourServiceFacade {
    /**
     * Get opening hours for a restaurant.
     *
     * @param id
     *            restaurant id
     * @return RestaurantOpeningHours
     */
    RestaurantOpeningHours getOpeningHours(String id);

    String getCustomizedOpeningHours(String id);

}
