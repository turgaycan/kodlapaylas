package com.kp.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.spring.cache.CacheBuilder;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import com.kp.controller.util.KpControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Date;

/**
 * Created by tcan on 07/02/16.
 */
@EnableScheduling
@EnableCaching
@Configuration
public class CouchbaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouchbaseConfiguration.class);

    public static final String KP_CACHE = "kpCache";
    public static final int KP_TTL = 2 * 60 * 60 * 1000; //Two hours

    @Value("${couchbase.cluster.ip}")
    private String ip;

    @Value("${com.couchbase.connectTimeout}")
    private long connectTimeout;

    @Bean(destroyMethod = "disconnect")
    public Cluster cluster() {
        final DefaultCouchbaseEnvironment couchbaseEnvironment = DefaultCouchbaseEnvironment.builder()
                .connectTimeout(connectTimeout).keepAliveInterval(connectTimeout)
                .build();

        return CouchbaseCluster.create(couchbaseEnvironment, ip);
    }

    @Bean(destroyMethod = "close")
    public Bucket defaultBucket() {
        return cluster().openBucket();
    }

    @Bean
    public CouchbaseCacheManager cacheManager() {
        final CacheBuilder cacheBuilder = CacheBuilder.newInstance(defaultBucket())
                .withExpirationInMillis((int) connectTimeout).withExpirationInMillis(KP_TTL);

        return new CouchbaseCacheManager(cacheBuilder, KP_CACHE);

    }

    @CacheEvict(allEntries = true, value = {KP_CACHE})
    @Scheduled(fixedDelay = 2 * 60 * 60 * 1000, initialDelay = 500)
    public void reportCacheEvict() {
        LOGGER.info("Flush Cache " + Date.from(Instant.now()));
    }

}
