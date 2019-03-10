package com.ratiose.testtask.service.tmdb;

public interface TmdbApi {
    String popularMovies();
    String findActorById(Long id);
    String findMovieById(Long id);
    String findByYear(int year);
}
