package com.example.microgram.controller;

import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import com.example.microgram.entity.User;
import com.example.microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService service;

    @GetMapping("/publicationByUser")
    public ResponseEntity<List<Publication>> userByName(@RequestParam int id){
        return new ResponseEntity<>(service.getPublicationByUser(id), HttpStatus.OK);
    }

    @GetMapping("/publicationByUser")
    public ResponseEntity<List<Publication>> userByName(@RequestParam Follow follow){
        return new ResponseEntity<>(service.getPublicationByFollowing(follow), HttpStatus.OK);
    }


}
