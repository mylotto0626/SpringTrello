package com.sparta.springtrello.domain.comment.repository;

import com.sparta.springtrello.domain.comment.entity.Commnet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommnetRepository extends JpaRepository<Commnet, Long> {
}
