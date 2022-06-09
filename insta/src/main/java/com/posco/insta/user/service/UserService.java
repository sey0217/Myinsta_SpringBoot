package com.posco.insta.user.service;

import com.posco.insta.user.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<UserDto> findUser();
    UserDto findUserById(UserDto userDto);

    Integer postUser(UserDto userDto);

    Integer deleteUserById(UserDto userDto);

    Integer updateUserById(Integer id);

    UserDto serviceLogin(UserDto userDto);

}
