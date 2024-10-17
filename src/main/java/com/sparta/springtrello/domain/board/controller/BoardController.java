package com.sparta.springtrello.domain.board.controller;


import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.response.SuccessResponse;
import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import com.sparta.springtrello.domain.board.dto.request.BoardUpdateRequest;
import com.sparta.springtrello.domain.board.dto.response.BoardDetailResponse;
import com.sparta.springtrello.domain.board.dto.response.BoardResponse;
import com.sparta.springtrello.domain.board.dto.response.BoardUpdateResponse;
import com.sparta.springtrello.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    // Board 생성!!
    @PostMapping
    public ResponseEntity<SuccessResponse<BoardResponse>> createBoard(
            @AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody BoardCreateDto request
    ) {
        BoardResponse response = boardService.createBoard(authUser,request);
        return ResponseEntity.ok(SuccessResponse.of(response));
    }


    // Board page 조회!
    @GetMapping
    public ResponseEntity<SuccessResponse<Page<BoardResponse>>> getBoards(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int pageNumber
    ) {
        Page<BoardResponse> boards = boardService.getBoards(pageSize, pageNumber);


        if (pageNumber < 1) {
            throw new IllegalArgumentException("페이지 번호는 1 이상이어야 합니다.");
        }

        return ResponseEntity.ok(SuccessResponse.of(boards));

    }


    // Board 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<SuccessResponse<BoardDetailResponse>> getBoard(@PathVariable Long boardId) {
        BoardDetailResponse boardDetail = BoardDetailResponse.from(boardService.getBoard(boardId));
        return ResponseEntity.ok(SuccessResponse.of(boardDetail));
    }


    // Board 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<SuccessResponse<BoardUpdateResponse>> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequest request) {
        BoardUpdateResponse response = BoardUpdateResponse.from(boardService.updateBoard(boardId, request));
        return ResponseEntity.ok(SuccessResponse.of(response));
    }

    // Board 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<SuccessResponse<Void>> deleteBoard(@PathVariable Long boardId
    ) {

        boardService.deleteBoard(boardId);
        return ResponseEntity.ok(SuccessResponse.of(null));
    }
}