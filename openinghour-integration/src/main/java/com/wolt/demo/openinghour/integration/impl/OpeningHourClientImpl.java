package com.wolt.demo.openinghour.integration.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wolt.demo.openinghour.domain.RestaurantOpeningHours;
import com.wolt.demo.openinghour.integration.OpeningHourClient;

/**
 * //FIXME: replace with proper implementation
 * <p>
 * Integrate data source of restaurant opening hours into this demo. An exception is thrown since no integration yet.
 * </p>
 *
 * @author Jia Li
 * @version 1.0
 */
@Component
public class OpeningHourClientImpl implements OpeningHourClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String NOT_SUPPORTED_MSG = "OpeningHourClientImpl.getOpeningHours Not supported yet.";

    @Override
    public RestaurantOpeningHours getOpeningHours(String id) {
        logger.error(NOT_SUPPORTED_MSG);
        throw new UnsupportedOperationException(NOT_SUPPORTED_MSG);
    }
}
