package com.ebelemgnegre.UserService.controller;

import com.ebelemgnegre.UserService.dto.UserDto;
import com.ebelemgnegre.UserService.dto.UserDtoResponse;
import com.ebelemgnegre.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDtoResponse> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(
                userService.addUser(userDto),
                HttpStatus.OK
        );
    }

    @PostMapping("/saveFavoriteMovies")
    public ResponseEntity<Void> saveFavoriteMovies(@RequestBody UserDto userDto) {
       userService.saveFavoriteMovie(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
