package com.ebelemgnegre.UserService.dto;

import com.ebelemgnegre.UserService.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MovieDto {
    private int id;
    private String originalLanguage;
    private String overview;
    private double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    private String releaseDate;
    private String title;
}
