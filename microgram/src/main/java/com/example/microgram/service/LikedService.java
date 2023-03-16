package com.example.microgram.service;

import com.example.microgram.dao.LikedDao;
import com.example.microgram.dao.PublicationDao;
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
    private Connection conn;
    @Autowired
    private LikedDao likedDao;

    public LikedService(LikedDao likedDao) {
        this.likedDao = likedDao;
    }

    public List<Liked> getLikes(Publication publication){
        return likedDao.getLikes(publication);
    }
}
