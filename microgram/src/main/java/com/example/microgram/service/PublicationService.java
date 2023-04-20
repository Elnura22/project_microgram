package com.example.microgram.service;

import com.example.microgram.dao.*;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.dto.PublicationDTOSecond;
import com.example.microgram.dto.PublicationImageDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import com.example.microgram.entity.PublicationImage;
import com.example.microgram.entity.User;
import com.example.microgram.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationDao publicationDao;
    private final UserDao userDao;
    private final CommentDao commentDao;
    private final LikedDao likedDao;

//сохраняем в базе массив байтов, мультипартфайл достаем айди картинки,
    // как передать логин и пароль через скрипт джс при запросе http basic
    public PublicationDTO addPublication(PublicationDTOSecond publicationDTOSecond,
                                         MultipartFile file,
                                         String email) throws IOException {
        String route = "/" + saveFile(file);
//        User user = userDao.findUserByEmail(email).orElseThrow();
        Publication publication = Publication.builder()
                .id(1L)
                .image(route)
                .description(publicationDTOSecond.getDescription())
//                .userId(user.getId())
                .userId(1L)
                .date(LocalDateTime.now())
                .build();
        publicationDao.save(publication);
//        user.setCounterPublication(user.getCounterPublication() + 1);
//        userDao.update(user);

        return PublicationDTO.from(publication);
    }

    public static String saveFile(MultipartFile file) throws IOException {
        File newFile = new File("microgram\\src\\main\\resources\\static\\images\\" +
//                UUID.randomUUID().toString().toLowerCase() +
                file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return newFile.getPath();
    }

    public boolean deletePublication(Long id, String email) {
        User user = userDao.findUserByEmail(email).orElseThrow();
        Publication publication = publicationDao.findById(id).orElseThrow();
        if (user.getId().equals(publication.getUserId())) {
            commentDao.deleteCommentByPublicationId(publication.getId());
            likedDao.deleteLikeOnPublication(publication.getId());
            publicationDao.deleteById(publication.getId());
            user.setCounterPublication(user.getCounterPublication() - 1);
            userDao.update(user);
        }
        return true;
    }

    public List<PublicationDTO> getOtherUsersPublications(int id) {
        List<Publication> publicationsList = publicationDao.getOtherUsersPublication(id);
        return publicationsList.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }

    public List<PublicationDTO> getAllPublications() {
        List<Publication> publicationsList = publicationDao.getAllPublications();
        return publicationsList.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
//
// function hideSplashScreen() {
// const splashScreen = document.getElementsByClassName('btn btn-info');
// const splashScreen = document.getElementById('splash');
// splashScreen.addEventListener('click', function () {
//     splashScreen.remove();
// });
// hideSplashScreen();
