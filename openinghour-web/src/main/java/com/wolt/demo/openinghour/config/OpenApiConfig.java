package com.wolt.demo.openinghour.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
    public static final String TAG_OPENING_HOUR = "OpeningHour";

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
        //@formatter:off
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("Opening Hours demo for wolt")
                        .description(appDesciption)
                        .contact(contact())
                        .version(appVersion)
                        .license(license()))
                .addTagsItem(new Tag().name(TAG_OPENING_HOUR)); //
        //@formatter:on
    }


    @Bean
    License license() {
        return new License() //
                .name("Wolt") //
                .url("http://wolt.com");
    }

    @Bean
    Contact contact() {
        Contact contact = new Contact();
        contact.name("lijiaa.li@gmail.ocm");
        contact.setUrl("https://fixme.wolt.com"); // FIXME: add project site
        return contact;
    }
}
