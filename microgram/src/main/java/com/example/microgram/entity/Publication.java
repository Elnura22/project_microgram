package com.example.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Publication {
    private Integer id;
    private String image;
    private String description;
    private LocalDateTime date;
}
