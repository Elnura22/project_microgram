package com.example.microgram.service;

import com.example.microgram.dao.LikedDao;
import com.example.microgram.dao.PublicationDao;
import com.example.microgram.dto.LikedDTO;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Liked;
import com.example.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedService {

    @Autowired
    private LikedDao likedDao;

    public LikedService(LikedDao likedDao) {
        this.likedDao = likedDao;
    }

    public List<Liked> getLikes(Publication publication){
        return likedDao.getLikes(publication);
    }
    public LikedDTO addLikeOnPublication(LikedDTO likedDTO) {
        var like = Liked.builder()
                .id(likedDTO.getId())
                .whoLiked(likedDTO.getWhoLiked())
                .whichPublication(likedDTO.getWhichPublication())
                .date(likedDTO.getDate())
                .build();
      likedDao.save(like);
      return LikedDTO.from(like);
    }
}
