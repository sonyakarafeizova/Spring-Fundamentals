package com.philately.controller;

import com.philately.model.dto.UserLoginDTO;
import com.philately.model.dto.UserRegisterDTO;
import com.philately.model.dto.UserResponseDTO;
import com.philately.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
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
    public String viewRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
            @Valid UserRegisterDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors() || !userService.register(data)) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }

//    @PostMapping("/login")
//    public String doLogin(
//            @Valid UserLoginDTO data,
//            BindingResult bindingResult,
//            RedirectAttributes redirectAttributes
//    ) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("loginData", data);
//            redirectAttributes.addFlashAttribute(
//                    "org.springframework.validation.BindingResult.loginData", bindingResult);
//
//            return "redirect:/login";
//        }
//
//        UserResponseDTO success = userService.loginUser(data);
//
//        if (success != null) {
//            return "redirect:/home";
//        }
//        redirectAttributes.addFlashAttribute("loginData", data);
//        redirectAttributes.addFlashAttribute("userPassMismatch", true);
//
//        return "redirect:login";
//
//    }

    @PostMapping("/logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }
}
