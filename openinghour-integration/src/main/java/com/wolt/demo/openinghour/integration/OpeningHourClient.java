package com.wolt.demo.openinghour.integration;
import com.wolt.demo.openinghour.domain.RestaurantOpeningHours;

public interface OpeningHourClient {
    RestaurantOpeningHours getOpeningHours(String id);
}