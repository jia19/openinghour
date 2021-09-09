package com.wolt.demo.openinghour.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wolt.demo.openinghour.domain.ActionType;
import com.wolt.demo.openinghour.domain.OpeningHour;
import com.wolt.demo.openinghour.domain.OpeningHourAction;
import com.wolt.demo.openinghour.domain.RestaurantOpeningHours;
import com.wolt.demo.openinghour.domain.WeekDay;
import com.wolt.demo.openinghour.integration.OpeningHourClient;
import com.wolt.demo.openinghour.service.OpeningHourServiceFacade;
import com.wolt.demo.openinghour.service.config.CacheConfig;

/**
 * Service facade implementation.
 *
 *
 */
@Service
public class OpeningHourServiceFacadeImpl implements OpeningHourServiceFacade {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

    @Autowired
    private OpeningHourClient openingHourClient;

    @Override
    @Cacheable(value = CacheConfig.CACHE_PROPERTY_NAME,
    key = "#id")
    public RestaurantOpeningHours getOpeningHours(String id) {
        return openingHourClient.getOpeningHours(id);
    }

    @Override
    public String getCustomizedOpeningHours(String id) {
        return getCustomizedString(openingHourClient.getOpeningHours(id));
    }

    private String getCustomizedString(RestaurantOpeningHours restaurantOpeningHours) {
        StringBuilder result = new StringBuilder();
        List<OpeningHour> openingHours = restaurantOpeningHours.getOpeningHours();
        for (int day = 0; day < restaurantOpeningHours.getOpeningHours().size(); day++) {
            OpeningHour currentDay = openingHours.get(day);

            List<OpeningHourAction> actions = currentDay.getActions();

            if (actions.isEmpty() || actions.size() == 1 && actions.get(0).getType() == ActionType.CLOSE) {
                result.append(currentDay.getWeekday().jsonValue()).append(": Closed\n");
            } else {
                int i = 0;
                // We cannot handle the first CLOSE for Monday because it should be checked if the last action in Sunday is OPEN
                if (day > 0 && actions.get(0).getType() == ActionType.CLOSE) {
                    result.append(timeFormatter.format(LocalDateTime.ofEpochSecond(actions.get(0).getValue(), 0, ZoneOffset.UTC)).replaceAll(":00", "")).append("\n");
                    i++;
                }

                result.append(currentDay.getWeekday().jsonValue()).append(": ");

                for (; i < actions.size(); i++) {
                    OpeningHourAction action = actions.get(i);

                    LocalDateTime actionTime = LocalDateTime.ofEpochSecond(action.getValue(), 0, ZoneOffset.UTC);
                    result.append(timeFormatter.format(actionTime).replaceAll(":00", ""));
                    if (action.getType() == ActionType.OPEN) {
                        result.append(" - ");
                    } else if (i + 1 < actions.size() && actions.get(i + 1).getType() == ActionType.OPEN) {// current action is CLOSE and next OPEN action exists
                        result.append(", ");
                    }
                }

                // if the last action is CLOSE
                if (actions.get(actions.size() - 1).getType() == ActionType.CLOSE) {
                    result.append("\n");
                }
            }
            // if the Sunday is ending with an OPEN action, we have to check the first CLOSE action in Monday
            if (currentDay.getWeekday() == WeekDay.SUNDAY && !openingHours.get(0).getActions().isEmpty() && openingHours.get(0).getActions().get(0).getType() == ActionType.CLOSE) {
                LocalDateTime actionTime = LocalDateTime.ofEpochSecond(openingHours.get(0).getActions().get(0).getValue(), 0, ZoneOffset.UTC);
                result.append(timeFormatter.format(actionTime).replaceAll(":00", "")).append("\n");
            }

        }
        return result.toString();
    }
}