package com.kp.service.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.cache.CouchbaseCacheManager;
import org.springframework.stereotype.Service;

/**
 * Created by tcan on 07/02/16.
 */
@Service
public class CacheService {

    @Autowired
    private CouchbaseCacheManager kpCacheManager;

}
