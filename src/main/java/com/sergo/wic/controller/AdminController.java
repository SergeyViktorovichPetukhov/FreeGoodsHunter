package com.sergo.wic.controller;

import com.sergo.wic.entities.*;
import com.sergo.wic.service.NotificationService;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    List<User> users = new ArrayList<>();
    List<Registration> registrations = new ArrayList<>();

    @GetMapping
    public String showAdminDashboard(Model model){
        users = userService.findAll();
        registrations = registrationService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("registrations", registrations);
        return "admin dashboard";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam String id){
        Optional<User> user = userService.findById(Long.valueOf(id));
        if (user.isPresent()){
            userService.confirmRegistration(user.get());
        }
        return "redirect:/admin/";
    }

    @GetMapping(value = "/show/{id}")
    public String showUser(@PathVariable("id") Long id, Model model){
        Optional<User> user = userService.findById(Long.valueOf(id));
        model.addAttribute("user",user.get());
        List<Share> shares = null;
        if (user.isPresent() & user.get().isHasCompany()){
         shares = user
                    .get()
                       .getCompany()
                          .getShares();
        }
        model.addAttribute("shares",shares);
        return "user info";
    }

    @PostMapping(value = "/show/{id}/cancel")
    public String cancelShare(@PathVariable("id") Long id,
                              @RequestParam("share_id") Long shareId,
                              @RequestParam("reason")  String reason,
                              Model model){

        shareService.cancelShare(Long.valueOf(shareId),reason);
        System.out.println(reason + " reason");
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        notificationService.save(new Notification(reason, user));
        model.addAttribute("shares",shareService.findAll());
        model.addAttribute("notificatations", notificationService.findAllByUser(user));
        return "user info";
    }

    @PostMapping(value = "/show/{id}/confirm")
    public String confirmShare(@PathVariable("id") Long id,
                               @RequestParam("share_id") Long shareId,
                               Model model){

        shareService.confirmShare(Long.valueOf(shareId));
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        notificationService.save(new Notification(THANKS_MESSAGE, user));
        model.addAttribute("shares",shareService.findAll());
        model.addAttribute("notificatations", notificationService.findAllByUser(user));
        return "user info";
        //    return "redirect:/admin/show/" + id;
    }


    @PostMapping("/refuse")
    public String refuseRegistration(@RequestParam("id") String id
                                   , @RequestBody String reason){
        Registration registration = registrationService.findByUserId(Long.valueOf(id));
        registration.setReasonOfRefuse(reason);
        registration.setNew(false);
        registrationService.save(registration);
        return "redirect:/admin/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(Long.valueOf(id));
        return "redirect:/admin/";
    }


}
