package com.example.microgram.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationImage {
    private Long id;
    private String name;

    @JsonProperty("poster_data")
    private byte[] posterData;

    public PublicationImage generateNoImage(){
        return PublicationImage.builder().name("-no-image").build();
    }
}
