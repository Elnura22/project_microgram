package com.example.microgram.service;

import com.example.microgram.dao.CommentDao;
import com.example.microgram.dao.PublicationDao;
import com.example.microgram.dao.UserDao;
import com.example.microgram.dto.CommentDTO;
import com.example.microgram.dto.CommentDTOSecond;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.entity.Comment;
import com.example.microgram.entity.Publication;
import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final UserDao userDao;

    private final PublicationDao publicationDao;


    public CommentDTO addComment(CommentDTOSecond commentData, Long id, String email) {
//        User user = userDao.findUserByEmail(email).orElseThrow();
//        Publication publication = publicationDao.findById(id).orElseThrow();
        Comment comment = Comment.builder()
                .id(1L)
                .text(commentData.getText())
//                .publicationId(publication.getId())
//                .userId(user.getId())
                .publicationId(2L)
                .userId(1L)
                .date(LocalDateTime.now())
                .build();
        commentDao.save(comment);
        return CommentDTO.from(comment);
    }

    public boolean deleteComment(Long id, String email) {
        User user = userDao.findUserByEmail(email).orElseThrow();
        Comment comment = commentDao.findById(id).orElseThrow();
        if (comment.getUserId().equals(user.getId())) {
            commentDao.deleteById(comment.getId());
        }
        return true;
    }

    public List<CommentDTO> getAllComments() {
        List<Comment> publicationsList = commentDao.getAllComments();
        return publicationsList.stream().map(CommentDTO::from).collect(Collectors.toList());
    }
}
