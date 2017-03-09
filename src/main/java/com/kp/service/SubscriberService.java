package com.kp.service;

import com.kp.domain.Subscriber;
import com.kp.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by turgaycan on 9/28/15.
 */
@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Transactional
    public Boolean addSubscriber(Subscriber subscriber){
        Subscriber persistedSubscriber  = subscriberRepository.save(subscriber);
        return persistedSubscriber.getId() != null;
    }

    @Transactional(readOnly = true)
    public boolean isExists(String email){
        return subscriberRepository.findByEmail(email) != null;
    }
}
