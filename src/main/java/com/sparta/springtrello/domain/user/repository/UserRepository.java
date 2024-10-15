package com.sparta.springtrello.domain.user.repository;

import com.sparta.springtrello.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Board, Long> {
}
