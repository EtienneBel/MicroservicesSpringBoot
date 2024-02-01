package com.ebelemgnegre.MovieService.controller;

import com.ebelemgnegre.MovieService.model.Movie;
import com.ebelemgnegre.MovieService.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllTopRatedMovies() {
        return new ResponseEntity<>(movieService.getAllTopRatedMovies(), HttpStatus.OK);
    }
}
