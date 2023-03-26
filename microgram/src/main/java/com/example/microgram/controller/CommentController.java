package com.example.microgram.controller;

import com.example.microgram.dto.CommentDTO;
import com.example.microgram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO addComment(@RequestBody CommentDTO commentData) {
        return service.addComment(commentData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        if (service.deleteComment(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
