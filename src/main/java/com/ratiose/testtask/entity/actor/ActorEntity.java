package com.ratiose.testtask.entity.actor;

import com.ratiose.testtask.entity.user.User;
import com.ratiose.testtask.entity.movie.MovieEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "actors")
public class ActorEntity {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String imdbId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MovieEntity> movies;

    public ActorEntity() {
    }

    public ActorEntity(String firstName) {
        this.name = firstName;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorEntity that = (ActorEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(imdbId, that.imdbId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imdbId, user, movies);
    }

    @Override
    public String toString() {
        return "ActorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", user=" + user +
                ", movies=" + movies +
                '}';
    }
}
