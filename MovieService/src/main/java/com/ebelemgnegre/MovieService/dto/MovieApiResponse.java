package com.ebelemgnegre.MovieService.dto;

import com.ebelemgnegre.MovieService.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieApiResponse {
    private int page;
    private List<Movie> results;
    private int totalPages;
    private int totalResults;
}
