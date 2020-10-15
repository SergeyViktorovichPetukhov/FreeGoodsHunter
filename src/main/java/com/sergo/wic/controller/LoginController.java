//package com.sergo.wic.controller;
//
////import com.sergo.wic.service.ModeratorService;
//import com.sergo.wic.service.RegistrationService;
//import com.sergo.wic.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import sun.rmi.runtime.Log;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    private ModeratorService moderatorService;
//    private UserService userService;
//    private RegistrationService registrationService;
//
//    public LoginController(@Autowired ModeratorService moderatorService,
//                           @Autowired RegistrationService registrationService,
//                           @Autowired UserService userService){
//        this.moderatorService = moderatorService;
//        this.userService = userService;
//        this.registrationService = registrationService;
//    }
//    @GetMapping
//    public String loginForm(Model model) {
//        //    model.addAttribute("moderator",new Moderator());
//        return "login";
//    }
//
//    @PostMapping
//    public String subscribe(@RequestParam String login,
//                            @RequestParam String password,
//                            Model model) {
//        if (moderatorService.checkLoginAndPassword(login, password)){
//            System.out.println("log and pass are valid");
//            UserDetails moder = moderatorService.loadUserByUsername(login);
//            model.addAttribute("users", userService.findAll());
//            model.addAttribute("registrations",registrationService.findAll());
//            return "admin dashboard";
//        }
//        return "login";
//    }
//}
