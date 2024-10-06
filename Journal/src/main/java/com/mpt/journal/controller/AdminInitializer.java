package com.mpt.journal.controller;

import com.mpt.journal.model.RoleModel;
import com.mpt.journal.model.UserModel;
import com.mpt.journal.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class AdminInitializer {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void createAdminUser() {
        if (userService.findByLogin("admin") == null) {
            RoleModel adminRole = new RoleModel("ROLE_ADMIN");
            UserModel adminUser = new UserModel("admin", "admin", "admin@gmail.com", adminRole);
            userService.createUser(adminUser);
            System.out.println("Администратор создан!");
        } else {
            System.out.println("Администратор уже существует.");
        }
    }
}

