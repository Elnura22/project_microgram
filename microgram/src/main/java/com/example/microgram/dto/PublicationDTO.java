package com.example.microgram.dto;

import com.example.microgram.entity.Publication;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTO {

    public static PublicationDTO from(Publication publication) {
        return builder()
                .id(publication.getId())
                .image(publication.getImage())
                .description(publication.getDescription())
                .userId(publication.getUserId())
                .date(publication.getDate())
                .build();
    }

    private Long id;
    private String image;
    private String description;
    @JsonProperty("user_id")
    private int userId;
    private LocalDateTime date;
}
