package com.sergo.wic.controller;

import com.sergo.wic.entities.*;
import com.sergo.wic.service.*;
import com.sergo.wic.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String THANKS_MESSAGE = "Спасибо за публикацию акции. Вы делаете всех нас лучше!";

    @Autowired
    private UserService userService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public String showAdminDashboard(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("registrations",registrationService.findAll());
        return "admin dashboard";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(
                                      @RequestParam String userId,
                                      @RequestParam(required = false) String one,
                                      @RequestParam(required = false) String two,
                                      @RequestParam(required = false) String three){
        Optional<User> user = userService.findById(Long.valueOf(userId));
        if (user.isPresent()){
            User u = user.get();
            if (one == null || two == null || three == null){
                registrationService.refuseRegistration(userId,"reason",user.get().getLogin());
                u.setCompanyRegInProcess(false);
                userService.save(u);
                return "redirect:/admin/";
            }
            userService.confirmRegistration(u);
        }
        return "redirect:/admin/";
    }

    @GetMapping(value = "/show/{id}")
    public String showUser(@PathVariable("id") Long id, Model model){
        Optional<User> user = userService.findById(Long.valueOf(id));
        List<Share> shares;
        if (user.isPresent() && user.get().isHasCompany()){
            model.addAttribute("user",user.get());
            Company company = user.get().getCompany();
            model.addAttribute("company",company);
            shares = user.get().getCompany().getShares();
            model.addAttribute("shares",shares);
        }
        return "user info";
    }

    @PostMapping(value = "/show/{id}/cancel")
    public String cancelShare(@PathVariable("id") Long id,
                              @RequestParam("share_id") Long shareId,
                              @RequestParam("reason")  String reason,
                              Model model){

        shareService.cancelShare(Long.valueOf(shareId),reason);
        notificationService.save(new Notification(reason,(User) model.getAttribute("user")));
        return "redirect:/admin/show/" + id;
    }

    @PostMapping(value = "/show/{id}/confirm")
    public String confirmShare(@PathVariable("id") Long id,
                               @RequestParam("share_id") Long shareId,
                               @RequestParam("reason")  String reason,
                               Model model
    ){

        shareService.confirmShare(Long.valueOf(shareId), reason);
        notificationService.save(new Notification(reason, (User) model.getAttribute("user")));
        return "redirect:/admin/show/" + id;
    }


    @PostMapping("/refuse")
    public String refuseRegistration(@RequestParam("id") String userId
                                   , @RequestBody String reason){
        User user = userService.findById(Long.valueOf(userId)).get();
        user.setCompanyRegInProcess(false);
        userService.save(user);
        registrationService.refuseRegistration(userId,reason,user.getLogin());
        return "redirect:/admin/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(Long.valueOf(id));
        return "redirect:/admin/";
    }


}
