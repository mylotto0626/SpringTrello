package com.sparta.springtrello.entity;

import com.sparta.springtrello.common.entity.Timestamped;
import com.sparta.springtrello.domain.card.entity.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    public Comment(String content, User user, Card card, String emoji){
        this.content = content;
        this.user = user;
        this.card = card;
        this.emoji = emoji;
    }

    public Comment(String content, String emoji) {
        this.content = content;
        this.emoji = emoji;
    }

    public void update(String content, String emoji) {
        this.content = content;
        this.emoji = emoji;
    }
}
