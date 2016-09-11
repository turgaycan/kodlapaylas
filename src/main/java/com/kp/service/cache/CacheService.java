package com.kp.service.cache;

import com.couchbase.client.CouchbaseClient;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.ops.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.cache.CouchbaseCacheManager;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by tcan on 07/02/16.
 */
@Service
public class CacheService {

    @Autowired
    private CouchbaseCacheManager kpCacheManager;


    public boolean set(String key, Serializable object) {
        final OperationFuture<Boolean> mergeObject = getCouchbaseClient().set(key, object);
        final OperationStatus operationStatus = mergeObject.getStatus();
        return operationStatus.isSuccess();
    }

    private CouchbaseClient getCouchbaseClient() {
        final CouchbaseClient couchbaseClient = kpCacheManager.getClients().get("kpCache");
        return couchbaseClient;
    }

}
