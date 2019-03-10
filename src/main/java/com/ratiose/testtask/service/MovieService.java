package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.entity.movie.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    MovieEntity createMovie(Long id);
    MovieEntity markAsWatched(Long id);
    List<MovieEntity> search (int year, ActorEntity actor, boolean watched);
}
