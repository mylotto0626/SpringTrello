package com.sparta.springtrello.domain.board.repository;

import com.sparta.springtrello.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByNameContainingAndDeletedAtIsNull(String name, Pageable pageable);

    Page<Board> findByDeletedAtIsNull(Pageable pageable);
}
