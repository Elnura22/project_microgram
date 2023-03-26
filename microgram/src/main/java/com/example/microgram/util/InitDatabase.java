package com.example.microgram.util;

import com.example.microgram.dao.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
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

//
//            List<User> users = Stream.generate(User::random)
//                    .limit(5)
//                    .collect(toList());
//            userDao.saveAll(users);
//
//            List<Publication> publications = Stream.generate(Publication::random)
//                    .limit(5)
//                    .collect(toList());
//            publicationDao.saveAll(publications);
        };
    }



    /*
   there is not enough data to create and fill the tables in this task,
   there are not enough methods for unloading / writing to the database,

   create tables and filling them

    INSERT INTO publications
VALUES ("someImage", "description" ..);

INSERT INTO users
VALUES ("someName", "someAccount", "someEmail", "somePassword",...);

INSERT INTO comments
VALUES ("someText", ..);

INSERT INTO follow
VALUES (someUserFollower, someUserFollowing, ...);

INSERT INTO liked
VALUES (someUserLiked, somePublication,...);
    */

}
