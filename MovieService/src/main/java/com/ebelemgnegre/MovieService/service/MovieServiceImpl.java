package com.ebelemgnegre.MovieService.service;

import com.ebelemgnegre.MovieService.dto.Movie;
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
public class MovieServiceImpl implements MovieService{

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public List<Movie> getAllTopRatedMovies() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + apiKey);

        RequestEntity<?> requestEntity=null;
        try {
            requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(apiUrl));
        }catch (URISyntaxException e){
            e.printStackTrace();
        }

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        MovieApiResponse movieApiResponse = new MovieApiResponse();
        try {
            System.out.println(responseEntity.getBody());
            movieApiResponse = objectMapper.readValue(responseEntity.getBody(), MovieApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Movie> movies = movieApiResponse.getResults();

        return movies;
    }
}
