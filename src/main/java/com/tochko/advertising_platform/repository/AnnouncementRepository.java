package com.tochko.advertising_platform.repository;

import com.tochko.advertising_platform.model.Announcement;
import com.tochko.advertising_platform.model.AnnouncementType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    Optional<Announcement> findById(Long id);
    Page<Announcement> findAll(Pageable pageable);
    @Query("SELECT ann FROM Announcement ann WHERE ann.type=?1")
    Page<Announcement> findByType(AnnouncementType type, Pageable pageable);
}
