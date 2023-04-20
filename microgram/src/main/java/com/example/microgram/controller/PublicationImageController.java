//package com.example.microgram.controller;
//
//import com.example.microgram.dto.PublicationImageDTO;
//import com.example.microgram.service.PublicationImageService;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//@CrossOrigin(origins = "http://http://localhost:63342", maxAge = 36000)
//@RestController
//@RequestMapping("/images")
//public class PublicationImageController {
//    private final PublicationImageService publicationImageService;
//
//    public PublicationImageController(PublicationImageService publicationImageService) {
//        this.publicationImageService = publicationImageService;
//    }
//
//    @PostMapping
//    public PublicationImageDTO addPublicationImage(@RequestParam("file") MultipartFile file) {
//        return publicationImageService.addImage(file);
//    }
//
//    @GetMapping("/{imageId}")
//    public ResponseEntity<Resource> serveFile(@PathVariable Long imageId) {
//        Resource resource = publicationImageService.getById(imageId);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
//                .body(resource);
//    }
//}
