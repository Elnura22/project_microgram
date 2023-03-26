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
public class Follow {
    private Long id;
    private int follower;
    private int following;
    @JsonProperty("date_time")
    private LocalDateTime date;


}
