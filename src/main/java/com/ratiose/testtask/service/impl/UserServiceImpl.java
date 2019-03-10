package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.entity.user.User;
import com.ratiose.testtask.repository.ActorRepository;
import com.ratiose.testtask.repository.MovieRepository;
import com.ratiose.testtask.repository.UserRepository;
import com.ratiose.testtask.service.ActorService;
import com.ratiose.testtask.service.UserService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ActorRepository actorRepository;
    private MovieRepository movieRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private TmdbApi tmdbApi;
    private ActorService actorService;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final ActorRepository actorRepository,
                           final MovieRepository movieRepository,
                           final TmdbApi tmdbApi,
                           final ActorService actorService) {
        this.userRepository = userRepository;
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.tmdbApi = tmdbApi;
        this.actorService = actorService;
    }

    @Override
    public User registerUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return null;
        }
        user = createUser(email, password);
        return userRepository.save(user);
    }

    @Override
    public User findUser(String email, String password) {
        User foundUser = userRepository.findByEmail(email);
        if (nonNull(foundUser)) {
            if (passwordEncoder.matches(password, foundUser.getPassword())) {
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        return Optional.ofNullable(userRepository.findByEmail(email))
                       .orElseThrow(Exception::new);
    }

    @Override
    public boolean addFavActor(Long userId, Long actorId) {
        ActorEntity actor;
        User user = userRepository.findOne(userId);
        actor = actorRepository.findOne(actorId);

        if (actor == null) {
            actor = actorService.createActor(actorId);
        }

        if (user != null && actor != null) {
            user.getFavActors().add(actor);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteActor(Long userId, Long actorId) {
        User user = userRepository.findOne(userId);
        ActorEntity actor = actorRepository.findOne(actorId);
        if (user != null && actor != null) {
            user.getFavActors().remove(actor);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}