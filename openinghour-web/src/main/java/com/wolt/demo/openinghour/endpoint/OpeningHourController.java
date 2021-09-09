package com.wolt.demo.openinghour.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wolt.demo.openinghour.config.OpenApiConfig;
import com.wolt.demo.openinghour.domain.RestaurantOpeningHours;
import com.wolt.demo.openinghour.exception.OpeningHourSystemException;
import com.wolt.demo.openinghour.service.OpeningHourServiceFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Implementation of getting opening hours end point.
 *
 *
 *
 * @author
 */
@RestController
@Tag(name = OpenApiConfig.TAG_OPENING_HOUR)
@Validated
public class OpeningHourController {

    private static final String STATUS_400_MESSAGE = "Request did not pass all validations. Error response contains details.";
    private static final String STATUS_500_MESSAGE = "Request failed due to unexpected system error.";

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String NO_REAL_DATA_MSG = "This demo does not have real data and thus an exception is thrown! id has to be 'mock' in this demo.";


    @Autowired
    private OpeningHourServiceFacade facade;

    @Operation(summary = "Get opening hours in Json.",
            description = "Get opening hours for a given restarant in Json. When `restaurantId` is `mock`, mocked data are returned, otherwise an exception is thrown as no real data in this demo.") //
    //@formatter:off
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Getting opening hours in Json done."),
            @ApiResponse(responseCode="400",description = STATUS_400_MESSAGE),
            @ApiResponse(responseCode="500",description = STATUS_500_MESSAGE)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/restaurants/json/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@formatter:on
    public RestaurantOpeningHours getJsonOpeningHours(
            @Parameter(description = "Id to get restaurant opening hours. `mock` must be used for fetching mocked restaurant data.") @PathVariable(name = "restaurantId") String restaurantId) {
        if (restaurantId != null && restaurantId.equals("mock")) {
            return facade.getOpeningHours(restaurantId);
        } else {
            logger.error(NO_REAL_DATA_MSG);
            throw new OpeningHourSystemException(NO_REAL_DATA_MSG);
        }
    }

    @Operation(summary = "Get opening hours.",
            description = "Get opening hours for a given restarant in Json formatter. When `restaurantId` is `mock`, mocked data are returned, otherwise an exception is thrown as no real data in this demo.") //
    //@formatter:off
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="Getting opening hours done."),
            @ApiResponse(responseCode="400",description = STATUS_400_MESSAGE),
            @ApiResponse(responseCode="500",description = STATUS_500_MESSAGE)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/restaurants/{restaurantId}", produces = MediaType.TEXT_PLAIN_VALUE)
    //@formatter:on
    public String getOpeningHours(
            @Parameter(description = "Id to get restaurant opening hours. `mock` must be used for fetching mocked restaurant data.") @PathVariable(name = "restaurantId") String restaurantId) {
        if (restaurantId != null && restaurantId.equals("mock")) {
            return facade.getCustomizedOpeningHours(restaurantId);
        } else {
            logger.error(NO_REAL_DATA_MSG);
            throw new OpeningHourSystemException(NO_REAL_DATA_MSG);
        }
    }


}