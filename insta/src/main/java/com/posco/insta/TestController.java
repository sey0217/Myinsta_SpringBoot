package com.posco.insta;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/")
    public String Test(){
        return "test1";
    }

//    @GetMapping("/{id}")
//    public String testId(@PathVariable String id){
//        return id;
//    }
//
//    @PostMapping("/")
//    public String testPost(){
//            return "hello world";
//    }
//
//
//    @DeleteMapping("/")
//    public String testDelete(){
//            return "hello world";
//    }
//
//    @PutMapping("/")
//    public String testPut(){
//            return "hello world";
//    }

}
