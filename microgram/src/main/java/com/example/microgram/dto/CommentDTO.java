package com.example.microgram.dto;

import com.example.microgram.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDTO {

    public static CommentDTO from(Comment comment) {
        return builder()
                .id(comment.getId())
                .text(comment.getText())
                .publicationId(comment.getPublicationId())
                .userId(comment.getUserId())
                .date(comment.getDate())
                .build();
    }

    private Long id;
    private String text;
    @JsonProperty("publication_id")
    private Long publicationId;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("date_time")
    private LocalDateTime date;
}
