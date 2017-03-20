package com.kp.service.cache;

import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import com.kp.config.CouchbaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by tcan on 07/02/16.
 */
@Service
public class CacheService {

    @Autowired
    private CouchbaseCacheManager cacheManager;

    public void set(String key, Serializable object) {
        getCache(CouchbaseConfiguration.KP_CACHE).put(key, object);
    }

    public Object get(String key) {
        return getCache(CouchbaseConfiguration.KP_CACHE).get(key).get();
    }

    private Cache getCache(String cacheName) {
        final Cache couchbaseClient = cacheManager.getCache(cacheName);
        return couchbaseClient;
    }


}
