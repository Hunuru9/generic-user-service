package com.hunuru.user_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
/**
 * Controller example to demonstrate security features.
 */
@RestController
public class UserController {

    @GetMapping("/me")
    public String getMyProfile(){
        return "Personal info";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers() {
        return "Admin-only: list of all users";
    }

    @GetMapping("/roles")
    public String getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
        }
        return "No roles found or user not authenticated";
    }
}
