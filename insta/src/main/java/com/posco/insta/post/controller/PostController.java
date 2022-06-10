package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.service.PostService;
import com.posco.insta.post.service.PostServiceImpl;
import com.posco.insta.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@TokenRequired
@Slf4j
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

    @GetMapping("/{id}")
    public SelectPostJoinUserDto getPostsById(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        return postService.getPostsById(postDto);
    }

    @DeleteMapping("/{id}")
    public Integer deleteMyPost(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(securityService.getIdAtToken());
        return postService.deletePostByUserIdAndId(postDto);
    }

    @GetMapping("/other")
    public List<SelectPostJoinUserDto> getOhterPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.findPostsByNotUserId(postDto);
    }

    @PutMapping("/{id}")
    //id를 받아서 PostDto의 img와 content바꾸기
    public Integer updateMyPost(
            @RequestBody PostDto postDto,
            @PathVariable String id
            ){
        //user의 id인 12
        postDto.setUserId(securityService.getIdAtToken());
        //posts의 id인 13
        postDto.setId(Integer.valueOf(id));

        log.info(postDto.toString());
        return postService.updateMyPost(postDto);
    }

    @PostMapping("/")
    public Integer postPost(@RequestBody PostDto postDto){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.insertPost(postDto);
    }

    @GetMapping("/key/{key}")
    public List<SelectPostJoinUserDto> getPostsLikekey(@PathVariable String key){
        return postService.finePostsLikeKey(key);

    }

    @GetMapping("/following")
    @Operation(description = "내가 following이고, follower인 id의 getPost")
    public List<SelectPostJoinUserDto> getPostsByMyFollowing(){
        postDto.setUserId(securityService.getIdAtToken());

        return postService.findPostsByFollowing(postDto);
    }



}
