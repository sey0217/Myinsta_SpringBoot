package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.service.PostService;
import com.posco.insta.post.service.PostServiceImpl;
import com.posco.insta.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@TokenRequired
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    PostDto postDto;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<PostDto> getPost(){
        return postService.getPosts();
    }

    @GetMapping("/my")
    @TokenRequired
    public List<SelectPostJoinUserDto> getPostByMyId(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostByUserId(postDto);
    }

    @DeleteMapping("/{id}")
    @TokenRequired
    public Integer deleteMyPost(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(securityService.getIdAtToken());
        return postService.deletePostByUserIdAndId(postDto);
    }

}
