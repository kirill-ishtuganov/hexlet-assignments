package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        return postRepository.findAll().stream()
                .map(post -> {
                    var commentsDTO = commentRepository.findByPostId(post.getId()).stream()
                            .map(comment -> new CommentDTO(comment.getId(), comment.getBody()))
                            .toList();
                    return new PostDTO(post.getId(), post.getTitle(), post.getBody(), commentsDTO);
                })
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable("id") long id) {
        var data = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var commentsDTO = commentRepository.findByPostId(data.getId()).stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getBody()))
                .toList();
        return new PostDTO(data.getId(), data.getTitle(), data.getBody(), commentsDTO);
    }
}
// END
