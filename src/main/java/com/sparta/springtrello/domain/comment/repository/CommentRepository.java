package com.sparta.springtrello.domain.comment.repository;

import com.sparta.springtrello.common.exception.ResponseCode;
import com.sparta.springtrello.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.NoSuchElementException;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException(ResponseCode.NOT_FOUND_USER.getMessage()));
    }

}
