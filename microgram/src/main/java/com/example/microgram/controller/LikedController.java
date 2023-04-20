package com.example.microgram.controller;

import com.example.microgram.dto.LikedDTO;
import com.example.microgram.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class LikedController {

    private final LikedService service;

//    @GetMapping("/likedBy")
//    public ResponseEntity<List<Liked>> userByName(@RequestParam Publication publication){
//        return new ResponseEntity<>(service.getLikes(publication), HttpStatus.OK);
//    }

    @PostMapping("/addLikeOnPublication/{id}")
    public ResponseEntity<String> addLikeOnPublication(@RequestBody LikedDTO likedDTO,
                                                       @PathVariable Long id,
                                                       Authentication authentication) {
        UserDetails ud = (UserDetails) authentication.getPrincipal();
        service.addLikeOnPublication(likedDTO, id, ud.getUsername());
        return ResponseEntity.ok().build();
    }

}
