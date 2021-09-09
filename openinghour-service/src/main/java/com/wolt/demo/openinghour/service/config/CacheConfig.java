package com.wolt.demo.openinghour.service.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String CACHE_PROPERTY_NAME = "com.wolt.demo.openinghour.cache";

    /**
     * Configuration for cache
     */
    @Component
    public class SimpleCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            cacheManager.setCacheNames(Arrays.asList(CACHE_PROPERTY_NAME));
        }
    }
}