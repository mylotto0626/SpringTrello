package com.sparta.springtrello.domain.card.repository;

import com.sparta.springtrello.domain.card.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c " +
            "JOIN c.list l " + // Card 엔티티에서 ListEntity로의 관계를 사용
            "JOIN c.user u " + // Card 엔티티에서 User로의 관계를 사용
            "WHERE l.id = :listId " +
            "AND u.id = :userId " +
            "AND (:title IS NULL OR c.title LIKE %:title%) " +
            "AND (:contents IS NULL OR c.contents LIKE %:contents%) " +
            "AND (:dueDate IS NULL OR c.dueDate = :dueDate) " +
            "AND (:assigneeName IS NULL OR u.nickName LIKE %:assigneeName%)") // User 엔티티의 nickName 필드로 수정
    Page<Card> searchCards(Long listId, Long userId, String title, String contents, String dueDate, String assigneeName, Pageable pageable);
}