package com.kp.service.cache;

import org.springframework.beans.factory.annotation.Autowired;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
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
        getKpCache().put(key, object);
    }

    public Object get(String key) {
        return getKpCache().get(key).get();
    }

    private Cache getKpCache() {
        final Cache couchbaseClient = cacheManager.getCache("kpCache");
        return couchbaseClient;
    }

}
