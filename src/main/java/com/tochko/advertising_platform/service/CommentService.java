package com.tochko.advertising_platform.service;

import com.tochko.advertising_platform.model.Comment;
import com.tochko.advertising_platform.model.User;
import com.tochko.advertising_platform.repository.CommentRepository;
import com.tochko.advertising_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentService implements CommonService<Comment>{
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.commentRepository=repository;
    }

    public Optional<Comment> get(long id) {
        return commentRepository.findById(id);
    }

    public Page<Comment> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment update(long id, Comment comment) {
        Optional<Comment> optComment = get(id);
        if(optComment.isPresent()) {
            Comment updatedComment = Comment.builder()
                    .id(id)
                    .message(comment.getMessage())
                    .modifiedDate(new Date())
                    .build();
            return commentRepository.save(updatedComment);
        }
        return null;
    }

    public void delete(long id) {
        Optional<Comment> deletedComment = get(id);
        deletedComment.ifPresent(commentRepository::delete);
    }
}
