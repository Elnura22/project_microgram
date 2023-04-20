package com.example.microgram.dto;

import com.example.microgram.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDTOSecond {

    public static CommentDTOSecond from(Comment comment) {
        return builder()
                .text(comment.getText())
                .build();
    }

    private String text;
}
