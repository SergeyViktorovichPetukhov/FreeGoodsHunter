package com.sergo.wic.controller;

import com.sergo.wic.entities.Registration;
import com.sergo.wic.entities.User;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    List<User> users = new ArrayList<>();
    List<Registration> registrations = new ArrayList<>();

    @GetMapping("/")
    public String showAdminDashboard(Model model){
        users = userService.findAll();
        registrations = registrationService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("registrations", registrations);
        return "admin dashboard";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam String id){
        User user = userService.findById(Long.valueOf(id));
        userService.confirmRegistration(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/show/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user",userService.findById(Long.valueOf(id)));
        return "user info";
    }


    @PostMapping("/refuse")
    public String refuseRegistration(@RequestParam("id") String id, @RequestBody String reason){
        Registration registration = registrationService.findByUserId(Long.valueOf(id));
        registration.setReasonOfRefuse(reason);
        registrationService.save(registration);
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(Long.valueOf(id));
        return "redirect:/admin/";
    }

}
