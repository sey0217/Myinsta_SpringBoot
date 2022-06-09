package com.posco.insta.user.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Integer postUser(@RequestBody UserDto userDto)
    {
        return userService.postUser(userDto);
    }

    @DeleteMapping("/{id}")
    public Integer deleteUser(UserDto userDto){
        return userService.deleteUser(userDto);
    }
    @PutMapping("/{id}")
    public Integer updateUserById(
            @RequestBody UserDto userDto,
            @PathVariable String id){
        return userService.updateUserById(Integer.valueOf(id));
    }
    @PostMapping("/login")
    public Map loginUser(@RequestBody UserDto userDto){
        UserDto loginUser = userService.serviceLogin(userDto);
        //System.out.println(loginUser.toString());
        String token = securityService.createToken(loginUser.getId().toString(),60*60*1000);
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

        String token = request.getHeader("token");
        String subject = securityService.getSubject(token);
        return subject;
    }

//    @GetMapping("/me")
//    @TokenRequired
//    public UserDto getUserByMe(){
//        UserDto userDto = new UserDto();
//        userDto.setId(Integer.valueOf());
//        return  userService.findUserById(userDto);
//    }
}
