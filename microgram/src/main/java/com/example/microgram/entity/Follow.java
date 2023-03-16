package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Follow {
    private Integer id;
    private User follower;
    private User following;
    private LocalDateTime date;

}
