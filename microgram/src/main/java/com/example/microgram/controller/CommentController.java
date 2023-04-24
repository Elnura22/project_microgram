package com.example.microgram.controller;

import com.example.microgram.dto.CommentDTO;
import com.example.microgram.dto.CommentDTOSecond;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.dto.PublicationDTOSecond;
import com.example.microgram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService service;

    @GetMapping("/allComments")
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        return new ResponseEntity<>(service.getAllComments(), HttpStatus.OK);
    }

    @PostMapping//(consumes = MediaType.APPLICATION_JSON_VALUE  /*, path = "{id}"*/)
    public CommentDTO addComment(//@RequestBody CommentDTOSecond commentData,
                                   @RequestParam("comment") String text
//                                 @PathVariable Long id
                                 /*Authentication authentication*/) {
//        UserDetails ud = (UserDetails) authentication.getPrincipal();
        String ud = "firstUser";
        Long id = 2L;
        CommentDTOSecond comment = CommentDTOSecond.builder()
                .text(text)
                .build();
        return service.addComment(comment, ud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication authentication) {
        UserDetails ud = (UserDetails) authentication.getPrincipal();
        if (service.deleteComment(id, ud.getUsername()))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "{id}")
//    public CommentDTO addComment(@RequestBody CommentDTO commentData, @PathVariable Long id, Authentication authentication) {
//        UserDetails ud = (UserDetails) authentication.getPrincipal();
//        return service.addComment(commentData, id,  ud.getUsername());
//    }