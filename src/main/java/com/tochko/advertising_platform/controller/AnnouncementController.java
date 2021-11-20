package com.tochko.advertising_platform.controller;

import com.tochko.advertising_platform.model.Announcement;
import com.tochko.advertising_platform.model.AnnouncementType;
import com.tochko.advertising_platform.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.*;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService annService;

    @Autowired
    public AnnouncementController(AnnouncementService service) {
        this.annService=service;
    }

    //get all announcements
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) AnnouncementType type,
            Model model) {
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

            model.addAttribute("announcements", announcements);
            if(type==AnnouncementType.ADVERTISING) {
                return "/advertising/advertisingList";
            }
            else if(type==AnnouncementType.PLATFORM) {
                return "/platform/platformList";
            }
            else {
                return "/announcement/announcementsList";
            }
        } catch (Exception e) {
            return "/announcement/announcementsList";
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

    // add announcement
    @GetMapping("/add")
    public String addForm(Model model,
                          @RequestParam AnnouncementType type) {
        model.addAttribute("announcement", new Announcement());
        if(type==AnnouncementType.PLATFORM) {
            return "platform/platformAdd";
        }
        else {
            return "advertising/advertisingAdd";
        }
    }
    @PostMapping("/add")
    public String add(
            @ModelAttribute Announcement announcement,
            @RequestParam AnnouncementType type,
            Model model) {
        try {
            announcement.setCreatedDate(new Date());
            announcement.setModifiedDate(new Date());
            announcement.setType(type);
            annService.add(announcement);
            if(type==AnnouncementType.PLATFORM) {
                return "redirect:/announcement/list?type=PLATFORM&size=10";
            }
            else {
                return "redirect:/announcement/list?type=ADVERTISING&size=10";
            }
        } catch (Exception e) {
            return "index";
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
