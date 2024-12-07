package com.example.Expense.Management.controller;

import com.example.Expense.Management.components.JWTUtils;
import com.example.Expense.Management.config.ApiVersionConfig;
import com.example.Expense.Management.dto.PasswordChangeDto;
import com.example.Expense.Management.dto.ReqRes;
import com.example.Expense.Management.dto.UserDTO;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.service.AuthService.OurUserDetailsService;
import com.example.Expense.Management.service.AuthService.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/auth")
@CrossOrigin("*")
@Slf4j
public class AuthenticationController {


    private final OurUserDetailsService ourUserDetailsService;
    private final JWTUtils jwtUtils;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public AuthenticationController(OurUserDetailsService ourUserDetailsService
            , JWTUtils jwtUtils) {
        this.ourUserDetailsService = ourUserDetailsService;
        this.jwtUtils = jwtUtils;
    }
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
//        return ResponseEntity.ok(authenticationService.register(reg));
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userInfo, HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            log.info("Authenticating user: {}", userInfo.getEmail());
            UserDetails user = ourUserDetailsService.loadUserByUsername(userInfo.getEmail());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
            log.info("User :- " + user);
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(userInfo.getEmail(), userInfo.getPassword()));
            log.info("Authentication : " + authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.isAuthenticated()) {
                return generateAuthResponse(user, request, response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
            }
        } catch (AuthenticationException e) {
            log.error("Authentication error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            log.error("Internal server error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    private ResponseEntity<?> generateAuthResponse(UserDetails username, HttpServletRequest request,
                                                   HttpServletResponse response) {

        String token = jwtUtils.generateToken(username);
        String refreshToken = jwtUtils.generateRefreshToken(username.getUsername());
        // CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
        // csrfTokenRepository.saveToken(csrfToken, request, response);

        addCookie(response, "JWT-TOKEN", token);
        addCookie(response, "UIDD", refreshToken);
        // addCookie(response, "XSRF-TOKEN", csrfToken.getToken());
        UserDTO user = new UserDTO();
        user.setEmail(username.getUsername());
        user.setRole(username.getAuthorities().iterator().next().getAuthority());
        return ResponseEntity.ok(user);
    }

    private void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(60); // 1 hour
        response.addHeader("Set-Cookie",
                String.format("%s=%s; Path=/; HttpOnly; Secure; SameSite=Strict", name, value));
    }


    @GetMapping("/check-auth")
    public ResponseEntity<?> checkAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken);

        if (isAuthenticated && authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            UserDTO user = new UserDTO();
            user.setEmail(userDetails.getUsername());
            User user1 = userService.getUserByUsername(userDetails.getUsername()) ;
            user.setName(user1.getName());
            user.setDepartment(user1.getDepartment());
            user.setDesignation(user1.getDesignation());
            user.setEmpId(user1.getEmpId());
            user.setRole(role);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok().build(); // Return empty response for unauthenticated users
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Clear the security context
        SecurityContextHolder.clearContext();

        // Remove all cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        // Invalidate the session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Return a success response
        return ResponseEntity.ok().body("Logged out successfully");
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(Principal principal) {
        // Ensure only an admin user can access the endpoint
        User loggedInUser = userService.getUserByUsername(principal.getName());
        if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Map User entities to UserDTO for minimal user data
        List<UserDTO> users = userService.getAllUsers().stream()
                .map(user -> new UserDTO(user.getEmail(), user.getRole(), user.getName(), user.getDesignation(), user.getEmpId(), user.getDepartment(), user.getDivision(), user.getLocation()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
//    @GetMapping("/profile")
//    public ResponseEntity<ReqRes> getProfile(Principal principal){
//        User user = userService.getUserByUsername(principal.getName());
//        return ResponseEntity.ok(authenticationService.getProfile(user));
//    }

    @PostMapping("/change-password")
    public ResponseEntity<ReqRes> changePassword(Principal principal, @RequestBody PasswordChangeDto passwordChangeDto) {
        ReqRes response = new ReqRes();
        boolean changed = userService.changePassword(principal.getName(), passwordChangeDto);
        if (changed) {
            response.setStatusCode(200);
            response.setMessage("Password changed successfully");
        } else {
            response.setStatusCode(400);
            response.setMessage("Failed to change password. Please check your input.");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/change-password/{userId}")
    public ResponseEntity<?> changePassword(@PathVariable String userId, @RequestBody PasswordChangeDto passwordChangeDto) {
        try {
            userService.changePassword(userId, passwordChangeDto.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            log.error("Error changing password", e);
            return ResponseEntity.badRequest().body("Error changing password: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting user", e);
            return ResponseEntity.badRequest().body("Error deleting user: " + e.getMessage());
        }
    }
    @GetMapping("/user-count")
    public ResponseEntity<Long> getUserCount() {
        long count = userService.getUserCount();
        return ResponseEntity.ok(count);
    }

}


