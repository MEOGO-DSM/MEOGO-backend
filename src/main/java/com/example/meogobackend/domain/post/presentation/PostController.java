package com.example.meogobackend.domain.post.presentation;

import com.example.meogobackend.domain.post.presentation.dto.request.PostRequest;
import com.example.meogobackend.domain.post.presentation.dto.response.PostListResponse;
import com.example.meogobackend.domain.post.presentation.dto.response.PostResponse;
import com.example.meogobackend.domain.post.presentation.dto.response.ReturnIdResponse;
import com.example.meogobackend.domain.post.service.PostDetailsService;
import com.example.meogobackend.domain.post.service.PostListService;
import com.example.meogobackend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostDetailsService postDetailsService;
    private final PostListService postListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnIdResponse create (@RequestBody @Valid PostRequest request) {
        return postService.create(request);
    }

    @GetMapping("/{id}")
    public PostResponse getPostDetails(@PathVariable @NotNull Long id) {
        return postDetailsService.getPostDetails(id);
    }

    @GetMapping("/search")
    public PostListResponse findPost(@RequestParam(value = "content") String content, Pageable page) {
        return postListService.findPost(content, page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        postService.delete(id);
    }

    @PatchMapping("/{id}")
    public Long update(@PathVariable @NotNull Long id, @RequestBody @Valid PostRequest request){
        return postService.update(id, request);
    }
}
