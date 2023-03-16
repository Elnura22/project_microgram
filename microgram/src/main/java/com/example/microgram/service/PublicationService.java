package com.example.microgram.service;

import com.example.microgram.dao.PublicationDao;
import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private Connection conn;
    @Autowired
    private PublicationDao publicationDao;

    public PublicationService(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public List<Publication> getPublicationByUser(int id){
        return publicationDao.getPublication(id);
    }

    public List<Publication> getPublicationByFollowing(Follow follow){
        return publicationDao.getPublicationsByFollowings(follow);
    }

}
