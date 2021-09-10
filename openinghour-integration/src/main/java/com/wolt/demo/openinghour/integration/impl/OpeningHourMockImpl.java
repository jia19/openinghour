package com.wolt.demo.openinghour.integration.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolt.demo.openinghour.domain.OpeningHour;
import com.wolt.demo.openinghour.domain.OpeningHourAction;
import com.wolt.demo.openinghour.domain.RestaurantOpeningHours;
import com.wolt.demo.openinghour.domain.WeekDay;
import com.wolt.demo.openinghour.exception.OpeningHourSystemException;
import com.wolt.demo.openinghour.integration.OpeningHourClient;
import com.wolt.demo.openinghour.integration.domain.mock.MockedOpeningHours;

@Primary
@Component
/**
 * Read mocked opening hours from mock file and map it to {@link com.wolt.demo.openinghour.domain.RestaurantOpeningHours}
 *
 * <p>
 * Move <code>@Primary</code> in this class to {@link com.wolt.demo.openinghour.integration.impl.OpeningHourClientImpl} when integration of the data source is done.
 * </p>
 *
 * <p>
 * Check <code>application.properties</code> for: <code>mock.file.path</code>
 * </p>
 *
 * @author Jia Li
 * @version 1.0
 */
public class OpeningHourMockImpl implements OpeningHourClient {

    @Value("${mock.file.path}")
    private String mockedFile;

    private static final String LOADING_MOCK_FAILED = "Loading mock object from '%s' failed.";
    private static final String FILE_NOT_EXIST = "Resource '%s' not found.";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper;

    public OpeningHourMockImpl() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public RestaurantOpeningHours getOpeningHours(String id) {
        MockedOpeningHours mockData = loadMockResourceObject(mockedFile, MockedOpeningHours.class);

        if (mockData == null) {
            mockData = new MockedOpeningHours();
        }

        return mapOpeningHours(mockData);
    }

    private <R> R loadMockResourceObject(String fromPath, Class<? extends R> clazz) {
        Resource resource = new ClassPathResource(fromPath);

        if (!resource.exists()) {
            String message = String.format(FILE_NOT_EXIST, fromPath);
            logger.error(message);
            return null;
        }

        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            String error = String.format(LOADING_MOCK_FAILED, fromPath);
            logger.error(error);
            throw new OpeningHourSystemException(error);
        }
    }

    private RestaurantOpeningHours mapOpeningHours(MockedOpeningHours mockedOpeningHours) {
        RestaurantOpeningHours openingHours = new RestaurantOpeningHours();

        // RestaurantOpeningHours preserve the ordering of elements in which they are inserted
        // OpeningHour sorts the actions according to open/close time.
        Collections.sort(mockedOpeningHours.getMonday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.MONDAY, mockedOpeningHours.getMonday()));

        Collections.sort(mockedOpeningHours.getTuesday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.TUESDAY, mockedOpeningHours.getTuesday()));

        Collections.sort(mockedOpeningHours.getWednesday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.WEDNESDAY, mockedOpeningHours.getWednesday()));

        Collections.sort(mockedOpeningHours.getThursday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.THURSDAY, mockedOpeningHours.getThursday()));

        Collections.sort(mockedOpeningHours.getFriday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.FRIDAY, mockedOpeningHours.getFriday()));

        Collections.sort(mockedOpeningHours.getSaturday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.SATURDAY, mockedOpeningHours.getSaturday()));

        Collections.sort(mockedOpeningHours.getSunday(), Comparator.comparingInt(OpeningHourAction::getValue));
        openingHours.getOpeningHours().add(new OpeningHour(WeekDay.SUNDAY, mockedOpeningHours.getSunday()));

        return openingHours;
    }
}
