package com.ebelemgnegre.UserService.service;

import com.ebelemgnegre.UserService.dto.UserDtoRequest;
import com.ebelemgnegre.UserService.dto.UserDtoResponse;
import com.ebelemgnegre.UserService.model.User;
import com.ebelemgnegre.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDtoResponse addUser(UserDtoRequest userDtoRequest) {
        User user = User.builder()
                .username(userDtoRequest.getUsername())
                .moviesIds(userDtoRequest.getMoviesIds())
                .build();

        user = userRepository.save(user);

        return UserDtoResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}
