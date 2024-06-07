package com.ebelemgnegre.UserService.service;

import com.ebelemgnegre.UserService.dto.UserDto;
import com.ebelemgnegre.UserService.dto.UserDtoResponse;

public interface UserService {
    UserDtoResponse addUser(UserDto userDto);

    UserDtoResponse saveFavoriteMovie(UserDto userDto);

}
