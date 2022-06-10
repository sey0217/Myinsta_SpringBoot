package com.posco.insta.follow.service;

import com.posco.insta.follow.model.FollowDto;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;

import java.util.List;

public interface FollowService {
    List<FollowDto> getFollowing(FollowDto followDto);
    List<FollowDto> getFollower(FollowDto followDto);
    FollowDto getFollowerByOne(FollowDto followDto);
    Integer insertFollow(FollowDto followDto);
    Integer deleteFollow(FollowDto followDto);

}
