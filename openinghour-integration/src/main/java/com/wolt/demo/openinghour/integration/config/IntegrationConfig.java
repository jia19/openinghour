package com.wolt.demo.openinghour.integration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Integration module configuration.
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.wolt.demo.openinghour.integration.impl" })
public class IntegrationConfig {
}
