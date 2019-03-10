package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.user.User;
import com.ratiose.testtask.entity.UserDataDTO;
import com.ratiose.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = POST)
    public ResponseEntity registerUser(@RequestBody
                                       UserDataDTO userDataDTO) {

        String email;
        String pass;

        if (userDataDTO != null) {
            email = userDataDTO.getEmail();
            pass = userDataDTO.getPassword();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (userService.registerUser(email, pass) != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/actor", method = PUT)
    public ResponseEntity addFavActor(@PathVariable Long userId,
                                      @RequestParam Long actorId) {

        if (userService.addFavActor(userId, actorId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/actor", method = DELETE)
    public ResponseEntity deleteActor(@PathVariable Long userId,
                                      @RequestParam Long actorId) {

        if (userService.deleteActor(userId, actorId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
