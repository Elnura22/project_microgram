package com.example.microgram.controller;

import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.dto.PublicationDTOSecond;
import com.example.microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService service;

    @GetMapping("{id}")
    public ResponseEntity<List<PublicationDTO>> getList(@PathVariable int id) {
        return new ResponseEntity<>(service.getOtherUsersPublications(id), HttpStatus.OK);
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<PublicationDTO>> getAllPublications(){
        return new ResponseEntity<>(service.getAllPublications(), HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PublicationDTO addPublication( // @RequestBody PublicationDTOSecond publicationDTOSecond,
                                         @RequestParam("description") String description,
                                         @RequestParam("image") MultipartFile file //id,
                                         /*Authentication authentication*/) throws IOException {
//        String ud = authentication.getName();
        String ud= "first";
        PublicationDTOSecond publicationDTOSecond = PublicationDTOSecond.builder()
//                .image(file.getBytes())
                .description(description)
                .build();

        return service.addPublication(publicationDTOSecond, file ,ud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id, Authentication authentication) {
        UserDetails ud = (UserDetails) authentication.getPrincipal();
        if (service.deletePublication(id, ud.getUsername()))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
