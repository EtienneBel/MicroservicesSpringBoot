package com.ebelemgnegre.UserService.service;

import com.ebelemgnegre.UserService.dto.MovieDto;
import com.ebelemgnegre.UserService.dto.UserDto;
import com.ebelemgnegre.UserService.dto.UserDtoResponse;
import com.ebelemgnegre.UserService.exception.CustomException;
import com.ebelemgnegre.UserService.model.Movie;
import com.ebelemgnegre.UserService.model.User;
import com.ebelemgnegre.UserService.repository.MovieRepository;
import com.ebelemgnegre.UserService.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

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
    public void saveFavoriteMovie(UserDto userDto) {
        try {

            MovieDto movieDto =  restTemplate.getForObject(
                    "http://MOVIE-SERVICE/movie/" + userDto.getMovieDto().getId(),
                    MovieDto.class
            );

            Movie movie = movieRepository.findByReferenceId(movieDto.getId());
            if (movie == null) {
                movie = movieRepository.save(Movie.builder()
                        .id(movieDto.getId())
                        .referenceId(movieDto.getId())
                        .build());
            }

            final Movie finalMovie = movie;

            userRepository.findById(userDto.getUserId()).ifPresent(user -> {
                if (!user.getMovies().contains(finalMovie)) {
                    List<Movie> movies = new ArrayList<>(user.getMovies());
                    movies.add(finalMovie);
                    user.setMovies(movies);
                    userRepository.save(user);
                }
            });

        } catch (Exception e) {
            throw new CustomException(e.getMessage(), "NOT_FOUND", 404);
        }
    }
}
