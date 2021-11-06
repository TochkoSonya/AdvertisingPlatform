package com.tochko.advertising_platform.controller;

import com.tochko.advertising_platform.model.Announcement;
import com.tochko.advertising_platform.model.AnnouncementType;
import com.tochko.advertising_platform.service.AnnouncementService;
import com.tochko.advertising_platform.service.CommonService;
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
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService annService;

    @Autowired
    public AnnouncementController(AnnouncementService service) {
        this.annService=service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Announcement>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) AnnouncementType type) {
        try {
            List<Announcement> announcements;
            Pageable paging = PageRequest.of(page, size);
            Page<Announcement> pageAnnouncements;

            if (type == null) {
                pageAnnouncements = annService.getAll(paging);
            }
            else {
                pageAnnouncements = annService.getByType(type, paging);
            }
            announcements = pageAnnouncements.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("announcements", announcements);
            response.put("currentPage", pageAnnouncements.getNumber());
            response.put("totalItems", pageAnnouncements.getTotalElements());
            response.put("totalPages", pageAnnouncements.getTotalPages());
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Announcement> get(
            @PathVariable("id") Long id) {
        try {
            Optional<Announcement> announcement = annService.get(id);
            return new ResponseEntity<>(announcement.get(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Announcement> add(
            @RequestBody Announcement announcement) {
        try {
            annService.add(announcement);
            return new ResponseEntity<>(announcement, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Announcement> update(
            @PathVariable("id") Long id,
            @RequestBody Announcement ann) {
        try {
                annService.update(id,ann);
            return new ResponseEntity<>(ann, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Announcement> delete(
            @PathVariable("id") Long id) {
        try {
                annService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
