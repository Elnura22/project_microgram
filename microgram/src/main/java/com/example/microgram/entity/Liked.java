package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Liked {
    private Integer id;
    private User whoLiked;

    private Publication publication;

    private LocalDateTime date;

}
