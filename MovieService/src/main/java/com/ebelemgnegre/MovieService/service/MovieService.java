package com.ebelemgnegre.MovieService.service;

import com.ebelemgnegre.MovieService.dto.MovieDto;
import com.ebelemgnegre.MovieService.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllTopRatedMovies();

    MovieDto getMovie(Long id);
}
