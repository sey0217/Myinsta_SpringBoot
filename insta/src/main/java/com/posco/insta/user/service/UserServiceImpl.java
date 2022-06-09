package com.posco.insta.user.service;

import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> findUser() {
        return userMapper.getUser();
    }

    @Override
    public UserDto findUserById(UserDto userDto) {
        return userMapper.getUserById(userDto);
    }

    @Override
    public Integer postUser(UserDto userDto) {
       return userMapper.postUser(userDto);
    }

    @Override
    public Integer deleteUserById(UserDto userDto) {
        return userMapper.deleteUser(userDto);
    }

    @Override
    public Integer updateUserById(Integer id) {
        return userMapper.updateUserById(id);
    }

    @Override
    public  UserDto serviceLogin(UserDto userDto) {
        System.out.println(userDto.toString());
        System.out.println(userMapper.getUserByUserIdAndPassword(userDto).toString());
        return userMapper.getUserByUserIdAndPassword(userDto);

    }

}
