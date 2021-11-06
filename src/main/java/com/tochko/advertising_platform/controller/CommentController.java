package com.tochko.advertising_platform.controller;

import com.tochko.advertising_platform.model.Announcement;
import com.tochko.advertising_platform.model.Comment;
import com.tochko.advertising_platform.repository.AnnouncementRepository;
import com.tochko.advertising_platform.repository.CommentRepository;
import com.tochko.advertising_platform.service.AnnouncementService;
import com.tochko.advertising_platform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final AnnouncementService announcementService;

    @Autowired
    public CommentController(CommentService commentService, AnnouncementService announcementService) {
        this.commentService=commentService;
        this.announcementService=announcementService;
    }

//    @GetMapping("/{id}/comments")
//    public ResponseEntity<Comment> getCommentsByAnnouncement(
//            @PathVariable("id") long announcementId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size) {
//
//        try {
//            List<Comment> comments;
//            Pageable paging = PageRequest.of(page, size);
//
//            Page<Comment> pageComments;
//            pageComments = commentRepository.findByAnnouncementId(announcementId, paging);
//
//            comments = pageComments.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("comments", comments);
//            response.put("currentPage", pageComments.getNumber());
//            response.put("totalItems", pageComments.getTotalElements());
//            response.put("totalPages", pageComments.getTotalPages());
//            return new ResponseEntity(response, HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/{id}/comments")
//    public ResponseEntity<Comment> createComment(
//            @PathVariable("id") long announcementId,
//            @RequestBody Comment comment) {
//        try {
//            Optional<Announcement> optionalAnn = announcementRepository.findById(announcementId);
//            if (!optionalAnn.isPresent()) {
//                return ResponseEntity.unprocessableEntity().build();
//            }
//            comment.setAnnouncement(optionalAnn.get());
//            Comment newComment = commentRepository.save(comment);
//            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{annId}/comments/{commentId}")
//    public ResponseEntity<Comment> updateComment(
//            @PathVariable("addId") long annId,
//            @PathVariable("commentId") long commentId,
//            @RequestBody Comment commentRequest) {
//
//        try {
//            Announcement optionalBook = announcementRepository.findById(annId);
//            if (optionalBook==null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            Comment newComment = commentRepository.findById(commentId);
//            newComment.setMessage(commentRequest.getMessage());
//            commentRepository.save(newComment);
//            return new ResponseEntity<>(newComment, HttpStatus.OK);
//        }
//        catch(Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @DeleteMapping("/{annId}/comments/{commentId}")
//    public ResponseEntity<Comment> deleteComment(
//            @PathVariable("annId") long annId,
//            @PathVariable("commentId") long commentId) {
//        try {
//            Announcement optionalBook = announcementRepository.findById(annId);
//            if (optionalBook==null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            Comment deletedComment = commentRepository.findById(commentId);
//            commentRepository.delete(deletedComment);
//            return new ResponseEntity<>(deletedComment, HttpStatus.OK);
//        } catch (
//                Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
}

