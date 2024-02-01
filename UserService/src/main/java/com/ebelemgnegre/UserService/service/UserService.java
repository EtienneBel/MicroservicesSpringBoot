package com.ebelemgnegre.UserService.service;

import com.ebelemgnegre.UserService.dto.UserDtoRequest;
import com.ebelemgnegre.UserService.dto.UserDtoResponse;

public interface UserService {
    UserDtoResponse addUser(UserDtoRequest userDtoRequest);
}
