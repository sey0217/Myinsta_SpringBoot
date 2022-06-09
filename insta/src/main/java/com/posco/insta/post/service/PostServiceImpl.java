package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.repository.PostMapper;
import com.posco.insta.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostMapper postMapper;


    @Override
    public List<PostDto> getPosts() {
        return postMapper.getPosts();
    }

    @Override
    public List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto) {
        return postMapper.findPostsByUserId(postDto);
    }

    @Override
    public Integer deletePostByUserIdAndId(PostDto postDto) {
        return postMapper.deletePostByUserIdAndId(postDto);
    }

}
