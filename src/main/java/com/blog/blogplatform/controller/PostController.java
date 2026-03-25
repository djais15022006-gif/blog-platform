package com.blog.blogplatform.controller;

import com.blog.blogplatform.dto.PostRequest;
import com.blog.blogplatform.entity.Post;
import com.blog.blogplatform.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@Valid @RequestBody PostRequest request,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(postService.createPost(request, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id,
                                       @Valid @RequestBody PostRequest request,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(postService.updatePost(id, request, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, userDetails.getUsername());
        return ResponseEntity.ok(Map.of("message", "Post deleted"));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPosts(keyword));
    }
}