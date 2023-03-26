package com.example.microgram.controller;

import com.example.microgram.dto.CommentDTO;
import com.example.microgram.dto.FollowDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService service;

    @PostMapping("/followAction")
    public ResponseEntity<?> followAction(@RequestBody FollowDTO followDTO,
                                          @RequestParam Long id,
                                          @RequestParam Long id2) {
        return ResponseEntity.ok(service.followAction(followDTO, id, id2));
    }
}
