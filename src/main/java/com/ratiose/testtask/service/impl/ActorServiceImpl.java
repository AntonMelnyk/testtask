package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.repository.ActorRepository;
import com.ratiose.testtask.service.ActorService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private TmdbApi tmdbApi;
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public ActorEntity createActor(Long id) {
        Object object = null;
        ActorEntity actor = new ActorEntity();

        String tmdbResponse = tmdbApi.findActorById(id);
        if (tmdbResponse != null && StringUtils.isNotEmpty(tmdbResponse)) {
           try {
               object = new JSONParser().parse(tmdbResponse);
           } catch (ParseException e) {
               e.printStackTrace();
           }
            JSONObject json = (JSONObject) object;
            actor.setId(id);
            actor.setImdbId((String) json.get("imdb_id"));
            actor.setName((String) json.get("name"));
            actorRepository.save(actor);
        }
        return actor;
    }

    @Override
    public ActorEntity findByName(String name) {
        return Optional.ofNullable(actorRepository.findByName(name)).orElse(null);
    }
}
