package com.posco.insta.user.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<UserDto> getUser(){

        return  userService.findUser();
    }
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return  userService.findUserById(userDto);
    }
    @PostMapping("/")
    public ResponseEntity<?> postUser(@RequestBody UserDto userDto)
    {
        HttpStatus httpStatus = userService.postUser(userDto)==1?HttpStatus.CREATED: HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping("/{id}")
    @TokenRequired
    public Integer deleteUserById(UserDto userDto){
//        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return userService.deleteUserById(userDto);
    }

    @PutMapping("/{id}")
    @TokenRequired
    @Operation(description = "")
    public Integer updateUserById(
            @RequestBody UserDto userDto){

        return userService.updateUserById(securityService.getIdAtToken());
    }
    @PostMapping("/login")
    @Operation(description = "로그인")
    public Map loginUser(@RequestBody UserDto userDto){
        UserDto loginUser = userService.serviceLogin(userDto);
        //System.out.println(loginUser.toString());
        String token = securityService.createToken(loginUser.getId().toString());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("name",loginUser.getName());
        map.put("img",loginUser.getImg());
        return map;
    }

    //header는 post,get,delet,update에 다 있음
    //그래서 get 방식으로 보내는데
//    @GetMapping("/token")
//    @TokenRequired
//    public String getToken(@RequestParam(value = "token")String token){
//        String subject = securityService.getSubject(token);
//        return subject;
//    }

    @GetMapping("/token")
    @TokenRequired
    public String getToken(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization");
        String subject = securityService.getSubject(tokenBearer);
        return subject;
    }

    @GetMapping("/me")
    @TokenRequired
    public UserDto getUserByMe(){
        //실행 로직
        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return  userService.findUserById(userDto);
    }
    @TokenRequired
    @GetMapping("/check")
    public Boolean check(){
        return true;
    }
}
