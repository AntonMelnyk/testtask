package com.ratiose.testtask.entity.user;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.entity.movie.MovieEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String email;

    private String password;

    @Column
    private String token;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<MovieEntity> movies;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ActorEntity> favActors;

    public Set<ActorEntity> getFavActors() {
        return favActors;
    }

    public void setFavActors(Set<ActorEntity> favActors) {
        this.favActors = favActors;
    }

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}