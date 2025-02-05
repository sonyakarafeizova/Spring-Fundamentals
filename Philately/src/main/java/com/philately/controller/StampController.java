package com.philately.controller;

import com.philately.model.dto.AddStampDTO;
import com.philately.model.entity.PaperType;
import com.philately.service.StampService;
import com.philately.service.UserService;
import com.philately.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stamps")
public class StampController {

    private final StampService stampService;
    private final LoggedUser loggedUser;
    private final UserService userService;

    public StampController(StampService stampService, LoggedUser loggedUser, UserService userService) {
        this.stampService = stampService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @ModelAttribute("addStampDTO")
    public AddStampDTO init() {
        return new AddStampDTO();
    }

    @ModelAttribute("papers")
    public PaperType[] getPaperTypes() {
        return PaperType.values();
    }


    @GetMapping("/add-stamps")
    public String addStamp() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "add-stamp";
    }


    @PostMapping("/add-stamps")
    public String addStamp(@Valid @ModelAttribute("addStampDTO") AddStampDTO addStampDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addStampDTO", addStampDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addStampDTO", result);
            return "redirect:/stamps/add-stamps";
        }

        this.stampService.addStamp(addStampDTO, loggedUser.getId());
        return "redirect:/home";
    }


    @GetMapping("/add/{id}")
    public String addToWishList(@PathVariable Long id) {
        stampService.addToWishList(id, loggedUser.getId());
        return "redirect:/home";
    }


    @GetMapping("/remove/{id}")
    public String removeStamp(@PathVariable Long id) {
        stampService.removeFromWishListById(id, loggedUser.getId());
        return "redirect:/home";
    }


    @GetMapping("/buy-stamps")
    public String buyStamps() {
        stampService.buyStamps(loggedUser.getId());
        return "redirect:/home";
    }


    @GetMapping("/remove-from-wishlist/{id}")
    public String removeFromWishListById(@PathVariable Long id) {
        stampService.removeFromWishListById(id, loggedUser.getId());
        return "redirect:/home";
    }
}