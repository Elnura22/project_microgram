package com.example.microgram.dto;

import com.example.microgram.entity.Liked;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikedDTO {

    public static LikedDTO from(Liked liked) {

        return builder()
                .id(liked.getId())
                .whoLiked(liked.getWhoLiked())
                .whichPublication(liked.getWhichPublication())
                .date(liked.getDate())
                .build();
    }

    private Long id;
    @JsonProperty("who_liked")
    private int whoLiked;
    @JsonProperty("which_publication")
    private int whichPublication;
    private LocalDateTime date;
}
