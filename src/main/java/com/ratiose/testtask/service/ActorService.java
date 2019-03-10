package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.actor.ActorEntity;
import org.springframework.stereotype.Component;

public interface ActorService {
    ActorEntity createActor(Long id);
    ActorEntity findByName(String name);
}
