package com.wolt.demo.openinghour.application;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application starter class.
 *
 * 
 */
@SpringBootApplication(scanBasePackages = { "com.wolt.demo.openinghour.config","com.wolt.demo.openinghour.endpoint" })
public class Application {
    public static void main(String[] args) {
        ToStringBuilder.setDefaultStyle(ToStringStyle.NO_CLASS_NAME_STYLE); // Sets the toString style for the whole app

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}