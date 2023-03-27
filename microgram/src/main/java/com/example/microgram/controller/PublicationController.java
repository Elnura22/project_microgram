package com.example.microgram.controller;

import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService service;


    @GetMapping("{id}")
    public ResponseEntity<List<PublicationDTO>> getList(@PathVariable int id){
        return new ResponseEntity<>(service.getOtherUsersPublications(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addPublication(@RequestBody PublicationDTO publicationData) {
        return service.addPublication(publicationData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        if (service.deletePublication(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
