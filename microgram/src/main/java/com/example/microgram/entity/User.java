package com.example.microgram.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String account;
    private String email;
    private String password;
    private Integer counterPublication;
    private Integer counterFollower;
    private Integer counterFollowing;

}
