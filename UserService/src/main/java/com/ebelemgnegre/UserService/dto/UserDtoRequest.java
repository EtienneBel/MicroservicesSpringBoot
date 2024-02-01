package com.ebelemgnegre.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoRequest {
        private long userId;
        private String username;
        private List<Long> moviesIds;
    }
