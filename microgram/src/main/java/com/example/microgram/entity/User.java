package com.example.microgram.entity;

import com.example.microgram.util.Generator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String account;
    private String email;
    private String password;
    @JsonProperty("counter_publication")
    private int counterPublication;
    @JsonProperty("counter_follower")
    private int counterFollower;
    @JsonProperty("counter_following")
    private int counterFollowing;

}
