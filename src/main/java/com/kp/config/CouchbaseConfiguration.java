package com.kp.config;

import com.couchbase.client.CouchbaseClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.cache.CouchbaseCacheManager;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tcan on 07/02/16.
 */
@EnableCaching
@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {


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
        instances.put("kpCache", couchbaseClient());
        return new CouchbaseCacheManager(instances);
    }

}
