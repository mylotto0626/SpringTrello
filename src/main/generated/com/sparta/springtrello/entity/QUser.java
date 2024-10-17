package com.sparta.springtrello.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1645349181L;

    public static final QUser user = new QUser("user");

    public final com.sparta.springtrello.common.entity.QTimestamped _super = new com.sparta.springtrello.common.entity.QTimestamped(this);

    public final EnumPath<com.sparta.springtrello.domain.user.authority.Authority> authority = createEnum("authority", com.sparta.springtrello.domain.user.authority.Authority.class);

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    public final ListPath<com.sparta.springtrello.domain.card.entity.Card, com.sparta.springtrello.domain.card.entity.QCard> cards = this.<com.sparta.springtrello.domain.card.entity.Card, com.sparta.springtrello.domain.card.entity.QCard>createList("cards", com.sparta.springtrello.domain.card.entity.Card.class, com.sparta.springtrello.domain.card.entity.QCard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.sparta.springtrello.domain.user.authority.MemberAuthority> memberAuthority = createEnum("memberAuthority", com.sparta.springtrello.domain.user.authority.MemberAuthority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickName = createString("nickName");

    public final StringPath pw = createString("pw");

    public final StringPath refreshToken = createString("refreshToken");

    public final BooleanPath status = createBoolean("status");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

