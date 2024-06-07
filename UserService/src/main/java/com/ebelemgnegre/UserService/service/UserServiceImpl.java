package com.ebelemgnegre.UserService.service;

import com.ebelemgnegre.UserService.dto.MovieDto;
import com.ebelemgnegre.UserService.dto.UserDto;
import com.ebelemgnegre.UserService.dto.UserDtoResponse;
//import com.ebelemgnegre.UserService.exception.CustomException;
import com.ebelemgnegre.UserService.model.Movie;
import com.ebelemgnegre.UserService.model.User;
import com.ebelemgnegre.UserService.repository.MovieRepository;
import com.ebelemgnegre.UserService.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDtoResponse addUser(UserDto userDto) {
        log.info("Adding user");
        User user = User.builder()
                .username(userDto.getUsername())
                .build();

        user = userRepository.save(user);

        return UserDtoResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }

    @Override
    public UserDtoResponse saveFavoriteMovie(UserDto userDto) {
        log.error("1");
        MovieDto movieDto = null;
        try {
            movieDto = restTemplate.getForObject(
                    "http://MOVIE-SERVICE/movie/" + userDto.getMovieDto().getId(),
                    MovieDto.class
            );
            log.error("2");
        } catch (Exception e) {
            log.error("in catch");
//            throw new CustomException(e.getMessage(), "NOT_FOUND", 404);
        }

        log.error("3");
        Movie movie = movieRepository.findByReferenceId(movieDto.getId());
        if (movie == null) {
            movie = movieRepository.save(Movie.builder()
                    .id(movieDto.getId())
                    .referenceId(movieDto.getId())
                    .build());
            log.error("movie == null");
        }

        final Movie finalMovie = movie;

        log.error("4");
        userRepository.findById(userDto.getUserId()).ifPresent(user -> {
            if (!user.getMovies().contains(finalMovie)) {
                log.error("in !user.getMovies().contains(finalMovie)");
                List<Movie> movies = new ArrayList<>(user.getMovies());
                movies.add(finalMovie);
                user.setMovies(movies);
                userRepository.save(user);
            }
        });

        log.error("5");
        return UserDtoResponse.builder()
                .userId(1)
                .build();
    }
}
