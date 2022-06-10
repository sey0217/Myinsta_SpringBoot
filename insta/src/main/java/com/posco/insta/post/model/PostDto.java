package com.posco.insta.post.model;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PostDto {
    private Integer id;
    private Integer userId; //토큰에 있는 id
    private String img;
    private String content;



}
