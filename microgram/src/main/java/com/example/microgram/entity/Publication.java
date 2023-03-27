package com.example.microgram.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    private Long id;
    private String image;
    private String description;
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("date_time")
    private LocalDateTime date;


}
