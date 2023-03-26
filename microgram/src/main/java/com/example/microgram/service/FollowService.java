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
    @Autowired
    private FollowDao followDao;
    private UserDao userDao;

    public FollowService(FollowDao followDao, UserDao userDao) {
        this.followDao = followDao;
        this.userDao = userDao;
    }

    public FollowDTO followAction(FollowDTO followDTO, Long id, Long id2) {
        var follower = userDao.findUserById(id);
        var following = userDao.findUserById(id2);
        Follow follow = Follow.builder()
                .follower(follower.orElseThrow().getCounterFollower())
                .following(following.orElseThrow().getCounterFollowing())
                .date(followDTO.getDate())
                .build();
        followDao.save(follow);
        return FollowDTO.from(follow);
    }
}
