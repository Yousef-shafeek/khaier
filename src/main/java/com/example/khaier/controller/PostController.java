package com.example.khaier.controller;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final SuccessResponseFactory200 responseFactory;
    @GetMapping
    public ResponseEntity<?> getPosts(){
        List<PostResponseDto> response = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Posts returned successfully "));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> findPostById(@PathVariable Long postId){
        PostResponseDto response = postService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Post returned successfully "));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> savePost(@RequestBody PostRequestDto postRequestDto,@PathVariable Long userId){
        PostResponseDto response = postService.addNewPost(postRequestDto,userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Posts saved successfully "));
    }
}