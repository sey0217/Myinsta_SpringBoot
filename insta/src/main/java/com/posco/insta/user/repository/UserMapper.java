package com.posco.insta.user.repository;

import com.posco.insta.user.model.UserDto;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper //mybatis 쿼리 가져오겠다는 어노테이션
public interface UserMapper {
    List<UserDto> getUser();
    UserDto getUserById(UserDto userDto);

    Integer postUser(UserDto userDto);

    Integer deleteUser(UserDto userDto);

    Integer updateUserById(Integer id);

    UserDto getUserByUserIdAndPassword(UserDto userDto);


}
