package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.repository.PostMapper;
import com.posco.insta.user.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    @Override
    public List<SelectPostJoinUserDto> findPostsByNotUserId(PostDto postDto) {
        return postMapper.findPostsByNotUserId(postDto);
    }

    @Override
    public Integer updateMyPost(PostDto postDto) {
        return postMapper.updateMyPost(postDto);
    }

    @Override
    public Integer insertPost(PostDto postDto) {
        return postMapper.insertPost(postDto);
    }

    @Override
    public SelectPostJoinUserDto getPostsById(PostDto postDto) {
        return postMapper.getPostsById(postDto);
    }

    @Override
    public List<SelectPostJoinUserDto> finePostsLikeKey(String word) {
        return postMapper.finePostsLikeKey(word);
    }

    @Override
    public List<SelectPostJoinUserDto> findPostsByFollowing(PostDto postDto) {
        log.info(postMapper.findPostsByFollowing(postDto).toString());
        return postMapper.findPostsByFollowing(postDto);
    }

}
