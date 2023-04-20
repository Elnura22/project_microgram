package com.example.microgram.service;

import com.example.microgram.dao.LikedDao;
import com.example.microgram.dao.PublicationDao;
import com.example.microgram.dao.UserDao;
import com.example.microgram.dto.LikedDTO;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Liked;
import com.example.microgram.entity.Publication;
import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedService {

    private final LikedDao likedDao;
    private final UserDao userDao;
    private final PublicationDao publicationDao;


    public List<Liked> getLikes(Publication publication) {
        return likedDao.getLikes(publication);
    }

    public LikedDTO addLikeOnPublication(LikedDTO likedDTO, Long id, String email) {
        User user = userDao.findUserByEmail(email).orElseThrow();
        Publication publication = publicationDao.findById(id).orElseThrow();
                Liked like = Liked.builder()
                .id(likedDTO.getId())
                .whoLiked(user.getId())
                .whichPublication(publication.getId())
                .date(likedDTO.getDate())
                .build();
        likedDao.save(like);
        return LikedDTO.from(like);
    }
}
