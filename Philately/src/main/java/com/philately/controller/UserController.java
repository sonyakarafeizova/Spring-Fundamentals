package com.philately.controller;

import com.philately.model.dto.UserLoginDTO;
import com.philately.model.dto.UserRegisterDTO;
import com.philately.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO createEmptyDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginData")
    public UserLoginDTO loginData() {
        return new UserLoginDTO();
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerConfirm(
            @Valid @ModelAttribute("registerData") UserRegisterDTO registerDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors() || !userService.register(registerDTO)) {
            redirectAttributes.addFlashAttribute("registerData", registerDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData", result);
            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    // Handle login request
    @PostMapping("/login")
    public String loginConfirm(
            @Valid @ModelAttribute("loginData") UserLoginDTO loginDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", loginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginData", result);
            return "redirect:/users/login";
        }

        if (userService.loginUser(loginDTO) == null) {
            redirectAttributes.addFlashAttribute("loginData", loginDTO);
            redirectAttributes.addFlashAttribute("userPassMismatch", true);
            return "redirect:/users/login";
        }

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @ModelAttribute
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }

    @ModelAttribute
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }
}