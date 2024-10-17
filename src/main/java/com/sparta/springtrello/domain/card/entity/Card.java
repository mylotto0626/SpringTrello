package com.sparta.springtrello.domain.card.entity;

import com.sparta.springtrello.common.entity.Timestamped;
import com.sparta.springtrello.domain.card.dto.CardRequestDto;
import com.sparta.springtrello.entity.ListEntity;
import com.sparta.springtrello.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Card")
@Getter
@NoArgsConstructor
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;
    private String title;
    private String contents;
    private String dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListEntity list;

    @Builder
    public Card(CardRequestDto requestDto, ListEntity list) {
        this.title = title;
        this.contents = contents;
        this.dueDate = dueDate;
        this.list = list;
    }


    public void updateCard(String title, String contents, String dueDate) {
        this.title = title;
        this.contents = contents;
        this.dueDate = dueDate;
    }

}
