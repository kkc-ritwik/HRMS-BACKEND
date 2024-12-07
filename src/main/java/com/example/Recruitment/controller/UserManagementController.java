package com.example.Recruitment.controller;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.ReqRes;
import com.example.Recruitment.dto.ValidationGroups;
import com.example.Recruitment.model.User;
import com.example.Recruitment.service.UserService;
import com.example.Recruitment.service.UsersManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/auth")
@CrossOrigin("*")
public class UserManagementController {

    @Autowired
    private UsersManagementService usersManagementService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ReqRes> register(
            @Validated(ValidationGroups.Registration.class) @RequestBody ReqRes reg) {
        ReqRes response = usersManagementService.register(reg);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(
            @Validated(ValidationGroups.Login.class) @RequestBody ReqRes req) {
        ReqRes response = usersManagementService.login(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@Valid @RequestBody ReqRes req) {
        ReqRes response = usersManagementService.refreshToken(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        ReqRes response = usersManagementService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUserByID(@PathVariable String userId) {
        ReqRes response = usersManagementService.getUsersById(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable String userId, @Valid @RequestBody User reqres) {
        ReqRes response = usersManagementService.updateUser(userId, reqres);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        usersManagementService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<ReqRes> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            ReqRes response = new ReqRes();
            response.setStatusCode(401);
            response.setMessage("User not authenticated");
            return ResponseEntity.status(401).body(response);
        }

        String email = authentication.getName();
        User user = userService.getUserByUsername(email);
        if (user == null) {
            ReqRes response = new ReqRes();
            response.setStatusCode(404);
            response.setMessage("User not found");
            return ResponseEntity.status(404).body(response);
        }

        ReqRes response = usersManagementService.getProfile(user);
        return ResponseEntity.ok(response);
    }
}
