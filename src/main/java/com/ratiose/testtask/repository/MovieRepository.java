package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.movie.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    MovieEntity findByImdbId(String imdbId);
}
