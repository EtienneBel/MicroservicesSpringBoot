package com.ebelemgnegre.MovieService.service;

import com.ebelemgnegre.MovieService.dto.MovieApiResponse;
import com.ebelemgnegre.MovieService.dto.MovieDto;
import com.ebelemgnegre.MovieService.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public List<Movie> getAllTopRatedMovies() {
        ResponseEntity<String> responseEntity = fetchApi(apiUrl + "/top_rated?language=en-US&page=1");
        MovieApiResponse movieApiResponse = new MovieApiResponse();

        try {
            movieApiResponse = new ObjectMapper().readValue(responseEntity.getBody(), MovieApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieApiResponse.getResults();
    }

    @Override
    public MovieDto getMovie(Long id) {
        ResponseEntity<String> responseEntity = fetchApi(apiUrl + "/" + id);
        MovieDto movieDto = new MovieDto();

        try {
            movieDto = new ObjectMapper().readValue(responseEntity.getBody(), MovieDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieDto;
    }

    private ResponseEntity<String> fetchApi(String apiUrlArg) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + apiKey);

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new RestTemplate().exchange(
                    new RequestEntity<>(
                            headers, HttpMethod.GET, new URI(apiUrlArg)
                    ),
                    String.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
