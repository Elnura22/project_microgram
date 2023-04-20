package com.example.microgram.dto;

import com.example.microgram.entity.Follow;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class FollowDTO {
    public static FollowDTO from(Follow follow) {
        return builder()
                .id(follow.getId())
                .follower(follow.getFollower())
                .following(follow.getFollowing())
                .date(follow.getDate())
                .build();
    }

    private Long id;
    private Long follower;
    private Long following;
    private LocalDateTime date;
}
