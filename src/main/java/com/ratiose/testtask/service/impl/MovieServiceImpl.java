package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.entity.movie.MovieEntity;
import com.ratiose.testtask.repository.MovieRepository;
import com.ratiose.testtask.service.ActorService;
import com.ratiose.testtask.service.MovieService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TmdbApi tmdbApi;
    @Autowired
    private ActorService actorService;

    @Override
    public MovieEntity createMovie(Long id) {
        Object object = null;
        MovieEntity movie = new MovieEntity();

        String tmdbResponse = tmdbApi.findMovieById(id);
        if (tmdbResponse != null && StringUtils.isNotEmpty(tmdbResponse)) {
            try {
                object = new JSONParser().parse(tmdbResponse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject json = (JSONObject) object;
            movie.setId(id);
            movie.setImdbId((String) json.get("imdb_id"));
            movie.setName((String) json.get("name"));
            movieRepository.save(movie);
        }
        return movie;
    }

    @Override
    public MovieEntity markAsWatched(Long movieId) {
        MovieEntity movie = movieRepository.findOne(movieId);
        if (movie == null) {
           movie = createMovie(movieId);
        }
        movie.setWatched(true);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public List<MovieEntity> search(int year, ActorEntity actor, boolean watched) {
        Set<MovieEntity> moviesSet = new HashSet<>();
        Iterable<MovieEntity> movies = movieRepository.findAll();
        for (MovieEntity movie: movies) {
            String[] dateArray = movie.getReleaseDate().split("-");
            int releaseYear = Integer.valueOf(dateArray[dateArray.length-1]);
            if (releaseYear == year) {
                moviesSet.add(movie);
            }
        }
        return moviesSet.stream().filter(movieEntity -> movieEntity.isWatched())
                .filter(movieEntity -> movieEntity.getActors().contains(actor))
                .collect(Collectors.toList());
    }
}
