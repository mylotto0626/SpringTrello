package com.sparta.springtrello.domain.list.dto.response;

import com.sparta.springtrello.entity.ListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListResponse {
    
    private String title;
    private int listOrder;

    public ListResponse(ListEntity listEntity) {
        this.title = listEntity.getTitle();
        this.listOrder = listEntity.getListOrder();
    }
}
