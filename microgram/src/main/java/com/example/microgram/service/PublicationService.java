package com.example.microgram.service;

import com.example.microgram.dao.PublicationDao;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private Connection conn;
    @Autowired
    private PublicationDao publicationDao;

    public PublicationService(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

//    public List<Publication> getPublicationByUser(int id) {
//        return publicationDao.getPublication(id);
//    }
//
//    public List<Publication> getPublicationByFollowing(Follow follow) {
//        return publicationDao.getPublicationsByFollowings(follow);
//    }
    public PublicationDTO addPublication(PublicationDTO publicationData) {
        var publication = Publication.builder()
                .id(publicationData.getId())
                .image(publicationData.getImage())
                .description(publicationData.getDescription())
                .userId(publicationData.getUserId())
                .date(publicationData.getDate())
                .build();
        publicationDao.save(publication);
        return PublicationDTO.from(publication);
    }

    public boolean deletePublication(Long id) {
        publicationDao.deleteById(id);
        return true;
    }

    public List<PublicationDTO> getOtherUsersPublications(int id) {
        var publicationsList = publicationDao.getOtherUsersPublication(id);
        return publicationsList.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }

}
