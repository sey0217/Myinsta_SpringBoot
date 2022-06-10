package com.posco.insta.follow.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.follow.model.FollowDto;
import com.posco.insta.follow.service.FollowServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@TokenRequired
@RequestMapping("follow")
public class FollowController {

    @Autowired
    FollowDto followDto;

    @Autowired
    SecurityService securityService;

    @Autowired
    FollowServiceImpl followService;

    @Operation(description = "내 팔로워 받아오기")
    @GetMapping("/my/follower")
    public List<FollowDto> getMyFollower(){
        followDto.setFollowing(securityService.getIdAtToken());
        return followService.getFollower(followDto);
    }
    @Operation(description = "다른 사람의 id로 팔로워 받아오기")
    @GetMapping("/follower/{id}")
    public List<FollowDto> getFollowerById(@PathVariable String id){
        followDto.setFollowing(Integer.valueOf(id));
        return followService.getFollower(followDto);
    }
    @Operation(description = "내 팔로잉 받아오기")
    @GetMapping("/my/following")
    public List<FollowDto> getMyFollowing(){
        followDto.setFollowing(securityService.getIdAtToken());
        return followService.getFollowing(followDto);
    }
    @Operation(description = "다른 사람의 id로 팔로잉 받아오기")
    @GetMapping("/following/{id}")
    public List<FollowDto> getFollowingById(@PathVariable String id){
        followDto.setFollowing(Integer.valueOf(id));
        return followService.getFollowing(followDto);
    }
    @Operation(description = "내가 다른 사람을 팔로우함(팔로잉이 됨)")
    @PostMapping("/{id}")
    public Integer postFollow(@PathVariable String id){
        followDto.setFollowing(securityService.getIdAtToken());
        followDto.setFollower(Integer.valueOf(id));
        return followService.insertFollow(followDto);
    }
    @Operation(description = "내가 다른 사람을 팔로우 했는데 취소(팔로잉 취소)")
    @DeleteMapping("/{id}")
    public Integer deleteFollow(@PathVariable String id){
        followDto.setFollowing(securityService.getIdAtToken());
        followDto.setFollower(Integer.valueOf(id));
        return followService.deleteFollow(followDto);
    }

}
