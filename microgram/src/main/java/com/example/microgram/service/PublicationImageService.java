//package com.example.microgram.service;
//
//import com.example.microgram.dao.PublicationImageDao;
//import com.example.microgram.dto.PublicationImageDTO;
//import com.example.microgram.entity.PublicationImage;
//import com.example.microgram.exception.ResourceNotFoundException;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Service
//public class PublicationImageService {
//    private final PublicationImageDao publicationImageDao;
//
//    public PublicationImageService(PublicationImageDao publicationImageDao) {
//        this.publicationImageDao = publicationImageDao;
//    }
//
//    public PublicationImageDTO addImage(MultipartFile file) {
//        byte[] data = new byte[0];
//        try {
//            data = file.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (data.length == 0) {
//            throw new RuntimeException("error, this file exists or no content");
//            // TODO return no content or something or throw exception
//            //  which will be processed on controller layer
//        }
//
//        PublicationImage image = PublicationImage.builder()
//                .posterData(data)
//                .build();
//
//        Long savedPublicationImageId = publicationImageDao.save(image);
//
//        return PublicationImageDTO.builder()
//                .imageId(savedPublicationImageId)
//                .build();
//    }
//
//    public Resource getById(Long imageId) {
//        PublicationImage publicationImage = publicationImageDao.findById(imageId)
//                .orElseThrow(() -> new ResourceNotFoundException("Movie Image with " + imageId + " doesn't exists!"));
//        return new ByteArrayResource(publicationImage.getPosterData());
//    }
//}


// http://localhost:8080/swagger-ui.html
//dont save array of bytes in database
//указать до картинки путь и сохр в базе