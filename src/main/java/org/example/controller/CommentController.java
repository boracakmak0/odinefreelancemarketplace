package org.example.controller;

import org.example.model.Comment;
import org.example.repository.CommentRepository;
import org.example.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JobRepository jobRepository;

    // Builder
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        if (comment.getJobId() == null || !jobRepository.existsById(comment.getJobId())) {
            throw new RuntimeException("Job ID is missing or does not exist.");
        }

        if (comment.getComment() == null || comment.getComment().trim().isEmpty()) {
            throw new RuntimeException("Comment cannot be empty.");
        }

        comment.setCreatedDate(LocalDate.now());
        return commentRepository.save(comment);
    }

    // Hepsini okumak için
    @GetMapping("/job/{jobId}")
    public List<Comment> getCommentsByJobId(@PathVariable Long jobId) {
        return commentRepository.findByJobId(jobId);
    }

    // Güncelleme metodu
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            comment.setComment(updatedComment.getComment());
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }
}
