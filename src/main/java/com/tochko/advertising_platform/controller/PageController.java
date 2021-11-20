//package com.tochko.advertising_platform.controller;
//
//import com.tochko.advertising_platform.model.User;
//import com.tochko.advertising_platform.service.AnnouncementService;
//import com.tochko.advertising_platform.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class PageController {
//    private final UserService userService;
//    //private final AnnouncementService announcementService;
//
//    @Autowired
//    public PageController(UserService service) {
//        this.userService=service;
//    }
//
//    @GetMapping(value = {"/", "/index"})
//    public String index() {
//       // model.addAttribute("announcements", );
//        return "/index";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "/admin";
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return "/user";
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "/about";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "/login";
//    }
//
//    @GetMapping("/registration")
//    public String registration(Model model) {
////        if (securityService.isAuthenticated()) {
////            return "redirect:/";
////        }
//
//        model.addAttribute("userForm", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//     //   userValidator.validate(userForm, bindingResult);
//
////        if (bindingResult.hasErrors()) {
////            return "registration";
////        }
//        userService.save(userForm);
//        //securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//        return "redirect:/index";
//    }
//
////    @GetMapping("/403")
////    public String error403() {
////        return "/error/403";
////    }
//}
