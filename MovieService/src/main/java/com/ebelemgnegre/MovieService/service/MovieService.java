package com.ebelemgnegre.MovieService.service;

import com.ebelemgnegre.MovieService.dto.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllTopRatedMovies();
}
