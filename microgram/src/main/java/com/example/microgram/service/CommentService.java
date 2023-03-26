package com.example.microgram.service;

import com.example.microgram.dao.CommentDao;
import com.example.microgram.dto.CommentDTO;
import com.example.microgram.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public CommentDTO addComment(CommentDTO commentData) {
        var comment = Comment.builder()
                .id(commentData.getId())
                .text(commentData.getText())
                .publicationId(commentData.getPublicationId())
                .userId(commentData.getUserId())
                .date(commentData.getDate())
                .build();
        commentDao.save(comment);
        return CommentDTO.from(comment);
    }

    public boolean deleteComment(Long id) {
        commentDao.deleteById(id);
        return true;
    }
}
