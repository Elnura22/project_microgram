package com.example.microgram.dto;

import com.example.microgram.entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTOSecond {
    public static UserDTOSecond from(User user) {
        return builder()
                .name(user.getName())
                .account(user.getAccount())
                .email(user.getEmail())
                .build();
    }
    private String name;
    private String account;
    private String email;

}
