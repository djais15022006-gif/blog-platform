package com.blog.blogplatform.service;

import com.blog.blogplatform.dto.PostRequest;
import com.blog.blogplatform.entity.*;
import com.blog.blogplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostRequest request, String authorEmail) {
        User author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .summary(request.getSummary())
                .author(author)
                .build();

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post updatePost(Long id, PostRequest request, String authorEmail) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getEmail().equals(authorEmail))
            throw new RuntimeException("You can only edit your own posts");

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setSummary(request.getSummary());

        return postRepository.save(post);
    }

    public void deletePost(Long id, String authorEmail) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getEmail().equals(authorEmail))
            throw new RuntimeException("You can only delete your own posts");

        postRepository.delete(post);
    }

    public List<Post> searchPosts(String keyword) {
        return postRepository.findByTitleContainingIgnoreCase(keyword);
    }
}