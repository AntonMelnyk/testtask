package com.ratiose.testtask.entity.movie;

import com.ratiose.testtask.entity.actor.ActorEntity;
import com.ratiose.testtask.entity.user.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "movies")
public class MovieEntity {

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ActorEntity> actors;

    @Column
    private String releaseDate;

    @Column
    private boolean watched;

    @Column
    private String imdbId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public MovieEntity() {
    }

    public MovieEntity(String name, Set actors, String releaseDate) {
        this.name = name;
        this.actors = actors;
        this.releaseDate = releaseDate;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(Set<ActorEntity> actors) {
        this.actors = actors;
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
        MovieEntity that = (MovieEntity) o;
        return watched == that.watched &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(actors, that.actors) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(imdbId, that.imdbId) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, actors, releaseDate, watched, imdbId, user);
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actors=" + actors +
                ", releaseDate='" + releaseDate + '\'' +
                ", watched=" + watched +
                ", imdbId='" + imdbId + '\'' +
                '}';
    }
}
