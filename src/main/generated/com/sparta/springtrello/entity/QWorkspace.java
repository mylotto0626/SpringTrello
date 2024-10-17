package com.sparta.springtrello.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkspace is a Querydsl query type for Workspace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkspace extends EntityPathBase<Workspace> {

    private static final long serialVersionUID = 1449720989L;

    public static final QWorkspace workspace = new QWorkspace("workspace");

    public final com.sparta.springtrello.common.entity.QTimestamped _super = new com.sparta.springtrello.common.entity.QTimestamped(this);

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<User, QUser> members = this.<User, QUser>createList("members", User.class, QUser.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QWorkspace(String variable) {
        super(Workspace.class, forVariable(variable));
    }

    public QWorkspace(Path<? extends Workspace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkspace(PathMetadata metadata) {
        super(Workspace.class, metadata);
    }

}

