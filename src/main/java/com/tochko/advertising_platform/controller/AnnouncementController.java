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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService annService;

    @Autowired
    public AnnouncementController(AnnouncementService service) {
        this.annService=service;
    }


    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) AnnouncementType type,
            Model model) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Announcement> pageAnnouncements;
//            if (type == null) {
//                pageAnnouncements = annService.getAll(paging);
//            }
                pageAnnouncements = annService.getByType(type, paging);
            int totalPages = pageAnnouncements.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("announcements", pageAnnouncements);
            if(type==AnnouncementType.ADVERTISING) {
                return "/advertising/advertisingList";
            }
            else {
                return "/platform/platformList";
            }
        } catch (Exception e) {
            return "/index";
        }
    }

    @GetMapping("/get/{id}")
    public String get(Model model,
                      @PathVariable("id") Long id,
                      @RequestParam AnnouncementType type) {
        try {
            Optional<Announcement> announcement = annService.get(id);
            model.addAttribute("announcement", announcement.get());
            if(type==AnnouncementType.PLATFORM) {
                return "/platform/platformDetails";
            }
            else {
                return "/advertising/advertisingDetails";
            }
        } catch (Exception e) {
            return "/index";
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
