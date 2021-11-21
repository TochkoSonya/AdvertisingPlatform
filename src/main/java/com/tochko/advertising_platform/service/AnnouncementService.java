package com.tochko.advertising_platform.service;

import com.tochko.advertising_platform.model.Announcement;
import com.tochko.advertising_platform.model.AnnouncementType;
import com.tochko.advertising_platform.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService implements CommonService<Announcement>{
    private final AnnouncementRepository annRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository repository) {
        this.annRepository=repository;
    }

    public Optional<Announcement> get(long id) {
        return annRepository.findById(id);
    }

    public Page<Announcement> getAll(Pageable pageable) {
        return annRepository.findAll(pageable);
    }

    public Page<Announcement> getByType(AnnouncementType type, Pageable pageable) {
        return annRepository.findByType(type, pageable);
    }

    public void add(Announcement announcement) {
        annRepository.save(announcement);
    }

    public Announcement update(long id, Announcement ann) { //it's work
        Optional<Announcement> updatedAnn = get(id);
        if(updatedAnn.isPresent()) {
            Announcement announcement  = Announcement.builder()
                     .id(id)
                    .title(ann.getTitle())
                    .brand(ann.getBrand())
                    .createdDate(ann.getCreatedDate())
                    .modifiedDate(new Date())
                    .description(ann.getDescription())
                    .period(ann.getPeriod())
                    .price(ann.getPrice())
                    .type(ann.getType())
                    .user(ann.getUser())
                    .build();
            return annRepository.save(announcement);
        }
        return null;
    }

    public void delete(long id) {
        Optional<Announcement> deletedUser = get(id);
        deletedUser.ifPresent(annRepository::delete);
    }
}
