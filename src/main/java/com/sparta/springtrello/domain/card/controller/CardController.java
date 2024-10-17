package com.sparta.springtrello.domain.card.controller;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.domain.card.dto.CardRequestDto;
import com.sparta.springtrello.domain.card.dto.CardResponseDto;
import com.sparta.springtrello.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/{workspaceId}/{boardId}/{listId}/cards")
public class CardController {

    private final CardService cardService;

    // 카드 생성
    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto,
                                                      @PathVariable Long workspaceId,
                                                      @PathVariable Long boardId,
                                                      @PathVariable Long listId,
                                                      @AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok(cardService.createCard(requestDto, authUser.getId(), workspaceId, boardId, listId));
    }

    // 카드 수정
    @PutMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long workspaceId,
                                                      @PathVariable Long boardId,
                                                      @PathVariable Long listId,
                                                      @PathVariable Long cardId,
                                                      @RequestBody CardRequestDto requestDto,
                                                      @AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok(cardService.updateCard(requestDto, authUser.getId(), workspaceId, boardId, listId, cardId));
    }

    // 카드 상세 정보 조회
    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> getCardDetail(@PathVariable Long workspaceId,
                                                         @PathVariable Long boardId,
                                                         @PathVariable Long listId,
                                                         @PathVariable Long cardId,
                                                         @AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok(cardService.getCardDetail(authUser.getId(), workspaceId, boardId, listId, cardId));
    }

    // 카드 삭제
    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable Long workspaceId,
                                             @PathVariable Long boardId,
                                             @PathVariable Long listId,
                                             @PathVariable Long cardId,
                                             @AuthenticationPrincipal AuthUser authUser) {
        cardService.deleteCard(authUser.getId(), workspaceId, boardId, listId, cardId);
        return ResponseEntity.ok("카드 삭제 완료");
    }
}
