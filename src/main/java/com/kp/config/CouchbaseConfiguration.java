package com.kp.config;

import com.couchbase.client.CouchbaseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.cache.CouchbaseCacheManager;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tcan on 07/02/16.
 */
@EnableScheduling
@EnableCaching
@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouchbaseConfiguration.class);

    public static final String KP_CACHE = "kpCache";

    @Value("${couchbase.cluster.username}")
    private String username;

    @Value("${couchbase.cluster.password}")
    private String password;

    @Value("${couchbase.cluster.ip}")
    private String ip;

    @Value("${couchbase.cluster.bucket}")
    private String bucketName;

    @Override
    protected List<String> bootstrapHosts() {
        return Arrays.asList(this.ip);
    }

    @Override
    protected String getBucketName() {
        return this.bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return this.password;
    }

    protected String getUsername() {
        return this.username;
    }

    @Bean
    public CouchbaseCacheManager kpCacheManager() throws Exception {
        HashMap<String, CouchbaseClient> instances = new HashMap();
        instances.put(
                KP_CACHE, couchbaseClient());
        return new CouchbaseCacheManager(instances);
    }

    @CacheEvict(allEntries = true, value = {KP_CACHE})
    @Scheduled(fixedDelay = 2 * 60 * 60 * 1000, initialDelay = 500)
    public void reportCacheEvict() {
        LOGGER.info("Flush Cache " + Date.from(Instant.now()));
    }

}
