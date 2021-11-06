package com.tochko.advertising_platform.controller;

import com.tochko.advertising_platform.model.User;
import com.tochko.advertising_platform.service.CommonService;
import com.tochko.advertising_platform.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    private final CommonService<User> userService;

    @Autowired
    public UserController(UserService service) {
        this.userService=service;
    }


    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
            List<User> users;
            Pageable paging = PageRequest.of(page, size);

            Page<User> pageUsers = userService.getAll(paging);
            users = pageUsers.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("users", users);
            response.put("currentPage", pageUsers.getNumber());
            response.put("totalItems", pageUsers.getTotalElements());
            response.put("totalPages", pageUsers.getTotalPages());
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> get(
            @PathVariable("id") Long id){
        try {
           Optional<User> user = userService.get(id);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(
            @RequestBody User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
            userService.add(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(
            @PathVariable("id") Long id,
            @RequestBody User user) {
        try {           
            User updatedUser = userService.update(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable("id") Long id) {
        try {
                userService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
