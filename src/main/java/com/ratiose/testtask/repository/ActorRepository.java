package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.actor.ActorEntity;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<ActorEntity, Long> {
    ActorEntity findByImdbId(String imdbId);
    ActorEntity findByName(String string);
}
