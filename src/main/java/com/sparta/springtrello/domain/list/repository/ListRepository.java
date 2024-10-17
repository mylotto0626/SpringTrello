package com.sparta.springtrello.domain.list.repository;

import com.sparta.springtrello.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<ListEntity, Long> {

    // 수정: 반환 타입을 List<ListEntity>로 변경
    List<ListEntity> findAllByBoardIdOrderByListOrder(Long boardId);

    List<ListEntity> findByBoardIdOrderByListOrder(Long boardId);
}