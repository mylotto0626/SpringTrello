package com.sparta.springtrello.domain.list.repository;

import com.sparta.springtrello.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<ListEntity,Long> {

    ListEntity findAllByBoardId(Long boardId);

    List<ListEntity> findByBoardIdOrderByListOrder(Long listId);
}
