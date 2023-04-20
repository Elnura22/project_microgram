package com.example.microgram.dto;

import com.example.microgram.entity.Publication;
import lombok.*;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTOSecond {
    public static PublicationDTOSecond from(Publication publication) {
        return builder()
                .description(publication.getDescription())
                .build();
    }
    private String description;
}
