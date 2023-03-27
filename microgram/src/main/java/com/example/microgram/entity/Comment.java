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
public class Comment {
    private Long id;
    private String text;
    @JsonProperty("publication_id")
    private int publicationId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("date_time")
    private LocalDateTime date;

}
