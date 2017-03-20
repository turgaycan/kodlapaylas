package com.kp.service.cache;

import com.couchbase.client.spring.cache.CouchbaseCache;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


/**
 * Created by tcan on 05/03/17.
 */
@RunWith(PowerMockRunner.class)
public class CacheServiceTest {

    @InjectMocks
    private CacheService cacheService;

    @Mock
    private CouchbaseCacheManager cacheManager;

    @Test
    public void shouldSetValue() {
        Cache mockBucket = mock(Cache.class);
        when(cacheManager.getCache("kpCache")).thenReturn(mockBucket);

        cacheService.set("turgay", "can");

        ArgumentCaptor<Serializable> argumentCaptor = ArgumentCaptor.forClass(Serializable.class);
        verify(mockBucket).put(eq("turgay"), argumentCaptor.capture());

        assertEquals("can", argumentCaptor.getValue());
    }

    @Test
    public void shouldGetValue() {
        Cache mockBucket = mock(Cache.class);
        when(cacheManager.getCache("kpCache")).thenReturn(mockBucket);
        when(mockBucket.get("turgay")).thenReturn(new SimpleValueWrapper("can"));

        final Object result = cacheService.get("turgay");

        assertEquals("can", result);
    }

}