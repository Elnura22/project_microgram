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
public class Liked {
    private Long id;
    @JsonProperty("who_liked")
    private Long whoLiked;
    @JsonProperty("which_publication")
    private Long whichPublication;
    @JsonProperty("date_time")
    private LocalDateTime date;

}
