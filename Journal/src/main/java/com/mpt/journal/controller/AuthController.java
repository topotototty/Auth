package com.mpt.journal.controller;

import com.mpt.journal.model.RoleModel;
import com.mpt.journal.model.UserModel;
import com.mpt.journal.service.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "reg";
    }

    @PostMapping("/reg")
    public String register(@Valid @ModelAttribute("userModel") UserModel userModel,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "reg";
        }

        if (userService.findByLogin(userModel.getLogin()) != null) {
            model.addAttribute("loginError", "Этот логин уже занят. Пожалуйста, выберите другой.");
            return "reg";
        }

        if (userService.findByEmail(userModel.getEmail()) != null) {
            model.addAttribute("emailError", "Этот email уже используется. Пожалуйста, выберите другой.");
            return "reg";
        }

        RoleModel userRole = new RoleModel("ROLE_USER");
        userModel.setRoleModel(userRole);
        userService.createUser(userModel);

        return "redirect:/main/calculate";
    }
}
