package com.konzik.auth.controllers;

import com.konzik.Common.User;
import com.konzik.auth.services.KeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class KeyCloakController {

    @Autowired
    KeyCloakService service;

    @PostMapping
    public String createUser(@RequestBody User user) {
        service.createUser(user);
        return "User Added Successfully.";
    }

    @GetMapping(path = "/find/{username}")
    public List<UserRepresentation> getUser(@PathVariable("username") String username) {
        List<UserRepresentation> user = service.getUser(username);

        return user;
    }

    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        service.updateUser(userId, user);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId) {
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping(path = "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId) {
        service.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }
}