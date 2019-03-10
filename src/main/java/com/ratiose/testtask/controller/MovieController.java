package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.service.ActorService;
import com.ratiose.testtask.service.MovieService;
import com.ratiose.testtask.service.UserService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private UserService userService;
    @Autowired
    private TmdbApi tmdbApi;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/popular", method = POST)
    public ResponseEntity popular(@RequestParam String email,
                                  @RequestParam String password,
                                  HttpSession session) {
        if (userService.findUser(email, password) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String popularMovies = tmdbApi.popularMovies();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(popularMovies);
    }

    @RequestMapping(value = "/{movieId}/watch", method = PUT)
    public ResponseEntity markAsWatched(@PathVariable Long movieId) {

        if (movieService.markAsWatched(movieId) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @RequestMapping(value = "/search", method = GET)
    public ResponseEntity search(@RequestParam int year,
                                 @RequestParam String actorName,
                                 @RequestParam boolean watched) {
        ActorEntity actor = actorService.findByName(actorName);
        if (actor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Actor wasn't find");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieService.search(year, actor, watched));
    }
}
