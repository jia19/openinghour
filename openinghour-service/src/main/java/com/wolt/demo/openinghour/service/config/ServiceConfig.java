package com.wolt.demo.openinghour.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.wolt.demo.openinghour.integration.config.IntegrationConfig;

/**
 * Service module configuration.
 *
 * @author
 */
@Configuration
@Import({ IntegrationConfig.class, CacheConfig.class })
@ComponentScan(basePackages = { "com.wolt.demo.openinghour.service.impl" })
public class ServiceConfig {
}
