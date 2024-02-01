package com.ebelemgnegre.MovieService.service;

import com.ebelemgnegre.MovieService.model.Movie;
import com.ebelemgnegre.MovieService.dto.MovieApiResponse;
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + apiKey);

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new RestTemplate().exchange(
                    new RequestEntity<>(
                            headers, HttpMethod.GET, new URI(apiUrl)
                    ),
                    String.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        MovieApiResponse movieApiResponse = new MovieApiResponse();
        try {
            movieApiResponse = new ObjectMapper().readValue(responseEntity.getBody(), MovieApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieApiResponse.getResults();
    }
}
