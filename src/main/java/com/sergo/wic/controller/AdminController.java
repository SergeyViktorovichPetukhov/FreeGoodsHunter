package com.sergo.wic.controller;

import com.sergo.wic.entities.*;
import com.sergo.wic.service.*;
import com.sergo.wic.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String THANKS_MESSAGE = "Спасибо за публикацию акции. Вы делаете всех нас лучше!";

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private NotificationService notificationService;

//    @Autowired
//    private ModeratorService moderatorService;


    @GetMapping
    public String showAdminDashboard(Model model){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("registrations",registrationService.findAll());
        return "admin dashboard";
    }

    @GetMapping(value = "/showItems")
    public String showItems(Model model){
        model.addAttribute("items",itemService.findAll());
        return "items";
    }

    @GetMapping(value = "/showRegistrations")
    public String showRegistrations(Model model){
        model.addAttribute("registrations",registrationService.findAll());
        return "registrations";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(
                                      @RequestParam String userId,
                                      @RequestParam String regId,
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
            userService.confirmRegistration(u, regId);
        }
        return "redirect:/admin/";
    }

    @GetMapping(value = "/show/{id}")
    public String showUser(@PathVariable("id") Long id, Model model){
        Optional<User> user = userService.findById(Long.valueOf(id));
        List<Share> shares;
        if (user.isPresent()){
            model.addAttribute("user",user.get());
            if (user.get().isHasCompany()){
                Company company = user.get().getCompany();
                model.addAttribute("company",company);
                shares = user.get().getCompany().getShares();
                model.addAttribute("shares",shares);
            }
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
                               Model model){
        shareService.confirmShare(Long.valueOf(shareId), reason);
        notificationService.save(new Notification(reason, (User) model.getAttribute("user")));
        return "redirect:/admin/show/" + id;
    }


    @PostMapping("/refuse")
    public String refuseRegistration(@RequestParam("userId") String userId,
                                     @RequestParam("regId") String regId
                                   , @RequestParam("reason") String reason){
        System.out.println(userId + " userId");
        System.out.println(regId + " id");
        User user = userService.findById(Long.valueOf(userId)).get();
        user.setCompanyRegInProcess(false);
        userService.save(user);
        registrationService.refuseRegistration(regId,reason,user.getLogin());
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(Long.valueOf(id));
        return "redirect:/admin/";
    }


}
