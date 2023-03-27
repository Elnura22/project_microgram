package com.example.microgram.util;

import com.example.microgram.dao.*;
import com.example.microgram.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class InitDatabase {

    @Bean
    CommandLineRunner init(UserDao userDao, PublicationDao publicationDao, CommentDao commentDao, FollowDao followDao, LikedDao likedDao) {
        return (args) -> {
            userDao.createTable();
            publicationDao.createTable();
            commentDao.createTable();
            followDao.createTable();
            likedDao.createTable();

//            userDao.deleteAll();
//            publicationDao.deleteAll();
//            commentDao.deleteAll();
//            followDao.deleteAll();
//            likedDao.deleteAll();

        };
    }
}
