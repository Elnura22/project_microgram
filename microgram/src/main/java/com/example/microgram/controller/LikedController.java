package com.example.microgram.controller;

import com.example.microgram.entity.Liked;
import com.example.microgram.entity.Publication;
import com.example.microgram.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikedController {

    private final LikedService service;

    @GetMapping("/likedBy")
    public ResponseEntity<List<Liked>> userByName(@RequestParam Publication publication){
        return new ResponseEntity<>(service.getLikes(publication), HttpStatus.OK);
    }

}
