package com.example.microgram.controller;

import com.example.microgram.dto.LikedDTO;
import com.example.microgram.entity.Liked;
import com.example.microgram.entity.Publication;
import com.example.microgram.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class LikedController {

    private final LikedService service;

//    @GetMapping("/likedBy")
//    public ResponseEntity<List<Liked>> userByName(@RequestParam Publication publication){
//        return new ResponseEntity<>(service.getLikes(publication), HttpStatus.OK);
//    }

    @PostMapping("/addLikeOnPublication")
    public ResponseEntity<String> addLikeOnPublication(@RequestBody LikedDTO likedDTO) {
        service.addLikeOnPublication(likedDTO);
        return ResponseEntity.ok().build();
    }

}
