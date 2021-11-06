package com.tochko.advertising_platform.repository;

import com.tochko.advertising_platform.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByAnnouncementId(Long announcementId, Pageable pageable);
    Optional<Comment> findById(long id);
}
