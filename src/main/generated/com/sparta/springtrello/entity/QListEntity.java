package com.sparta.springtrello.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QListEntity is a Querydsl query type for ListEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QListEntity extends EntityPathBase<ListEntity> {

    private static final long serialVersionUID = 1935344409L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QListEntity listEntity = new QListEntity("listEntity");

    public final QBoard board;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> listOrder = createNumber("listOrder", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QListEntity(String variable) {
        this(ListEntity.class, forVariable(variable), INITS);
    }

    public QListEntity(Path<? extends ListEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QListEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QListEntity(PathMetadata metadata, PathInits inits) {
        this(ListEntity.class, metadata, inits);
    }

    public QListEntity(Class<? extends ListEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

