package com.sparta.springtrello.domain.list.controller;

import com.sparta.springtrello.common.enums.Authority;
import com.sparta.springtrello.common.response.SuccessResponse;
import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import com.sparta.springtrello.domain.board.dto.request.BoardUpdateRequest;
import com.sparta.springtrello.domain.board.dto.response.BoardDetailResponse;
import com.sparta.springtrello.domain.board.dto.response.BoardResponse;
import com.sparta.springtrello.domain.list.dto.request.ListCreateDto;
import com.sparta.springtrello.domain.list.dto.request.ListUpdateRequest;
import com.sparta.springtrello.domain.list.dto.response.ListDetailResponse;
import com.sparta.springtrello.domain.list.dto.response.ListResponse;
import com.sparta.springtrello.domain.list.service.ListService;
import com.sparta.springtrello.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class ListController {
    private final ListService listService;

    // 리스트 생성
    @PostMapping
    public ResponseEntity<SuccessResponse<ListResponse>> createList(
            User user, @Valid @RequestBody ListCreateDto request, Long boardId
    ) {

        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 리스트를 생성할 수 없습니다.");
        }

        ListResponse response = listService.createList(user.getId(), boardId, request);
        return ResponseEntity.ok(SuccessResponse.of(response));
    }


    // 보드 내 리스트 전체 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<ListDetailResponse>> getBoards(
            @PathVariable Long boardId
    ) {
        ListDetailResponse response = listService.getListsByBoard(boardId);
        return ResponseEntity.ok(SuccessResponse.of(response));
    }

    // 리스트 순서 변경
    @PutMapping("/order/{listId}")
    public ResponseEntity<SuccessResponse<Void>> changeListOrder(@PathVariable Long listId, @RequestParam int newOrder) {
        listService.changeListOrder(listId, newOrder);
        return ResponseEntity.ok(SuccessResponse.of(null));
    }

    // 라스트 수정
    @PutMapping("/edit/{listId}")
    public ResponseEntity<SuccessResponse<ListResponse>> updateList(
            User user,
            @PathVariable Long listId,
            @Valid @RequestBody ListUpdateRequest request
    ) {
        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 리스트를 수정할 수 없습니다");
        }

        ListResponse response = ListResponse.from(listService.updateList(listId, request, user.getId()));

        return ResponseEntity.ok(SuccessResponse.of(response));
    }


    // 리스트 삭제
    @DeleteMapping("/delete/{listId}")
    public ResponseEntity<SuccessResponse<Void>> deleteBoard(
            User user,
            @PathVariable Long listId
    ) {

        // 예외 처리: 읽기 전용 사용자가 리스트 삭제 시도 시 예외 발생
        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 리스트를 삭제할 수 없습니다.");
        }
        listService.deleteList(listId, user.getId());
        return ResponseEntity.ok(SuccessResponse.of(null));
    }
}
