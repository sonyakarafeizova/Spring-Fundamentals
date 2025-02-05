package com.philately.controller;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.service.StampService;
import com.philately.service.UserService;
import com.philately.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
private final LoggedUser loggedUser;
    private final UserSession userSession;
    private final UserService userService;
    private final StampService stampService;

    public HomeController(LoggedUser loggedUser, UserSession userSession, UserService userService, StampService stampService) {
        this.loggedUser = loggedUser;
        this.userSession = userSession;
        this.userService = userService;
        this.stampService = stampService;
    }

    @GetMapping("/")
    public String notLogged() {
        if (userSession.isUserLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String logged(Model model) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }


        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        assert user != null;

        List<Stamp> allStampsByUser = this.stampService.getAllStampsByUser((user.getId()));
        List<Stamp> allStamps = this.stampService.getAllStamps(user);
        Set<Stamp> userWishedStamps = this.stampService.getWishedStampsByUser(user.getId());
        Set<Stamp> boughtStampsByUser = this.stampService.getBoughtStampsByUser(user.getId());

        model.addAttribute("allStampsByUser", allStampsByUser);
        model.addAttribute("allStamps", allStamps);
        model.addAttribute("userWishedStamps", userWishedStamps);
        model.addAttribute("boughtStampsByUser", boughtStampsByUser);

        return "home";
    }

}
