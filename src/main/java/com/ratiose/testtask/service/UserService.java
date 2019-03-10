package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.user.User;

public interface UserService {
    User registerUser(String email, String password);
    User findUser(String email, String password);
    User findUserByEmail(String email) throws Exception;
    boolean addFavActor(Long userId, Long imdbId);
    boolean deleteActor(Long userId, Long imdbId);
}

