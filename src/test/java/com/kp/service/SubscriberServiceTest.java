package com.kp.service;

import com.kp.domain.Subscriber;
import com.kp.repository.SubscriberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


/**
 * Created by tcan on 07/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {

    @InjectMocks
    private SubscriberService service;

    @Mock
    private SubscriberRepository subscriberRepository;

    @Test
    public void shouldReturnTrueIfAddSubscriber() {
        final Subscriber subscriber = new Subscriber("email");
        subscriber.setId(1l);

        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);

        final Boolean isPersisted = service.addSubscriber(subscriber);

        assertTrue(isPersisted);
    }

    @Test
    public void shouldReturnFalseIfNotAddSubscriber() {
        final Subscriber subscriber = new Subscriber("email");

        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);

        final Boolean isPersisted = service.addSubscriber(subscriber);

        assertFalse(isPersisted);
    }

    @Test
    public void shouldReturnTrueIfIsExists() {
        final Subscriber subscriber = new Subscriber("email");
        when(subscriberRepository.findByEmail("email")).thenReturn(subscriber);
        final boolean isExists = service.isExists("email");

        assertTrue(isExists);
    }

    @Test
    public void shouldReturnFalseIfIsNotExist() {
        when(subscriberRepository.findByEmail("email")).thenReturn(null);
        final boolean isExists = service.isExists("email");

        assertFalse(isExists);
    }

}