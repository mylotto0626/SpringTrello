package com.sparta.springtrello.domain.board.controller;

import com.sparta.springtrello.common.response.SuccessResponse;
import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import com.sparta.springtrello.domain.board.dto.request.BoardUpdateRequest;
import com.sparta.springtrello.domain.board.dto.response.BoardDetailResponse;
import com.sparta.springtrello.domain.board.dto.response.BoardResponse;
import com.sparta.springtrello.domain.board.dto.response.BoardUpdateResponse;
import com.sparta.springtrello.domain.board.service.BoardService;
import com.sparta.springtrello.entity.Board;
import com.sparta.springtrello.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;


    // Board 생성
    @PostMapping
    public ResponseEntity<SuccessResponse<BoardResponse>> createBoard(
            User user, @Valid @RequestBody BoardCreateDto request
    ) {
        return ResponseEntity.ok(SuccessResponse.of(boardService.createBoard(user.getId(),request)));
    }


    // Board page 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<Page<BoardResponse>>> getBoards(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int pageNumber
    ) {
        return ResponseEntity.ok(SuccessResponse.of(boardService.getBoards(name,pageSize,pageNumber)));
    }


    // Board 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<SuccessResponse<BoardDetailResponse>> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(SuccessResponse.of(boardService.getBoard(boardId)));
    }


    // Board 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<SuccessResponse<BoardUpdateResponse>> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequest request) {
        return ResponseEntity.ok(SuccessResponse.of(boardService.updateBoard(boardId, request)));
    }

    // Board 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<SuccessResponse<Void>> deleteBoard(@PathVariable Long boardId
    ) {

        boardService.deleteBoard(boardId);
        return ResponseEntity.ok(SuccessResponse.of(null));
    }
}
