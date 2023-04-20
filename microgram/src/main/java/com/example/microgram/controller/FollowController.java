package com.example.microgram.controller;

import com.example.microgram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService service;

    @PostMapping("/followAction")
    public ResponseEntity<?> followAction(
                                          @RequestParam Long id, Authentication authentication) {
        UserDetails ud = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(service.followAction( id, ud.getUsername()));
    }
}
