package com.example.microgram.dto;

import com.example.microgram.entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {
    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .account(user.getAccount())
                .email(user.getEmail())
                .counterPublication(user.getCounterPublication())
                .counterFollower(user.getCounterFollower())
                .counterFollowing(user.getCounterFollowing())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .build();
    }

    private Long id;
    private String name;
    private String account;
    private String email;
    private Long counterPublication;
    private Long counterFollower;
    private Long counterFollowing;
    private String role;
    private boolean enabled;
}
