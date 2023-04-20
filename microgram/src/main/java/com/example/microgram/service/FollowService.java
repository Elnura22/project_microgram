package com.example.microgram.service;

import com.example.microgram.dao.FollowDao;
import com.example.microgram.dao.UserDao;
import com.example.microgram.dto.FollowDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.entity.Follow;
import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowDao followDao;
    private final UserDao userDao;


    public FollowDTO followAction( Long id, String email) {
        User follower = userDao.findUserByEmail(email).orElseThrow();
        User following = userDao.findUserById(id).orElseThrow();

        Follow follow = Follow.builder()
                .follower(follower.getId())
                .following(following.getId())
                .date(LocalDateTime.now())
                .build();

        if (follower.getId().equals(following.getId())) {
            throw new RuntimeException("You can not subscribe on yourself.");
        }
        followDao.save(follow);
        follower.setCounterFollowing(follower.getCounterFollowing() + 1);
        userDao.updateCounterFollowing(follower);
        following.setCounterFollower(following.getCounterFollower() + 1);
        userDao.updateCounterFollower(following);
        return FollowDTO.from(follow);
    }
//write logic for checking \Is user follow?
}
