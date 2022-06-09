package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.user.model.UserDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();


    List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto);

    Integer deletePostByUserIdAndId(PostDto PostDto);
}
