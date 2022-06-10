package com.posco.insta.post.repository;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostMapper {

    List<PostDto> getPosts();

    List<SelectPostJoinUserDto> findPostsByUserId(PostDto postDto);

    Integer deletePostByUserIdAndId(PostDto postDto);

    List<SelectPostJoinUserDto> findPostsByNotUserId(PostDto postDto);

    Integer updateMyPost(PostDto postDto);

    Integer insertPost(PostDto postDto);

    SelectPostJoinUserDto getPostsById(PostDto postDto);

    List<SelectPostJoinUserDto> finePostsLikeKey(String word);

    List<SelectPostJoinUserDto> findPostsByFollowing(PostDto postDto);


}
