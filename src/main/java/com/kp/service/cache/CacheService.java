package com.kp.service.cache;


import com.couchbase.client.spring.cache.CouchbaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by tcan on 07/02/16.
 */
@Service
public class CacheService {

    @Autowired
    private CouchbaseCacheManager cacheManager;

    public boolean set(String key, Serializable object) {
        getKpCache().put(key, object);
        return true;
    }

    public Object get(String key) {
        return getKpCache().get(key).get();
    }

    private CouchbaseCache getKpCache() {
        final CouchbaseCache couchbaseClient = (CouchbaseCache) cacheManager.getCache("kpCache");
        return couchbaseClient;
    }

}
