package com.sparta.springtrello.domain.list.controller;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.response.SuccessResponse;
import com.sparta.springtrello.domain.list.dto.request.ListCreateDto;
import com.sparta.springtrello.domain.list.dto.request.ListUpdateRequest;
import com.sparta.springtrello.domain.list.dto.response.ListDetailResponse;
import com.sparta.springtrello.domain.list.dto.response.ListResponse;
import com.sparta.springtrello.domain.list.service.ListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class ListController {
    private final ListService listService;

    // 리스트 생성
    @PostMapping
    public ResponseEntity<String> createList(
            AuthUser authUser, @Valid @RequestBody ListCreateDto request
    ) {
        listService.createList(authUser, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("리스트 생성 완료");
    }

    // 리스트 조회
    @GetMapping
    public ResponseEntity<ListDetailResponse> getList(
            @RequestParam Long boardId
    ) {
        ListDetailResponse response = listService.getListsByBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 리스트 순서 변경
    @PutMapping("/order/{listId}")
    public ResponseEntity<SuccessResponse<ListResponse>> changeListOrder(
            @PathVariable Long listId, @RequestParam int newOrder
    ) {
        listService.changeListOrder(listId, newOrder);
        return ResponseEntity.ok(SuccessResponse.of(null));
    }

    // 리스트 수정
    @PutMapping("/edit/{listId}")
    public ResponseEntity<SuccessResponse<ListResponse>> updateList(
            @PathVariable Long listId, @Valid @RequestBody ListUpdateRequest request
    ) {
        ListResponse response = listService.updateList(listId, request);
        return ResponseEntity.ok(SuccessResponse.of(response));
    }

    // 리스트 삭제
    @DeleteMapping("/delete/{listId}")
    public ResponseEntity<SuccessResponse<ListResponse>> deleteList(
            @AuthenticationPrincipal AuthUser authUser, @PathVariable Long listId
    ) {
        listService.deleteList(authUser, listId);
        return ResponseEntity.ok(SuccessResponse.of(null));
    }
}
