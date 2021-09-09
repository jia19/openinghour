package com.wolt.demo.openinghour.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wolt.demo.openinghour.service.config.ServiceConfig;

/**
 * Configuration class for application web module.
 *
 * @author
 */
@Configuration
@Import({ ServiceConfig.class }) // Import beans from service module
public class WebConfig implements WebMvcConfigurer {
    protected static final int COOKIE_MAX_AGE = -1;

    /**
     * Responses are always in JSON with this configuration.
     *
     */
    // @Override
    // public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    // configurer.defaultContentType(APPLICATION_JSON);
    // }

}