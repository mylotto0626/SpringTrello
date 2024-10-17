package com.sparta.springtrello.domain.comment.controller;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.domain.comment.dto.request.UpdateCommentRequestDto;
import com.sparta.springtrello.domain.comment.dto.request.addCommentRequestDto;
import com.sparta.springtrello.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/cards/{card_id}/comments")
    public ResponseEntity<String> addComment(@AuthenticationPrincipal AuthUser authUser,
                                             @PathVariable("card_id") long cardId,
                                             @RequestBody addCommentRequestDto addcommentRequestDto){
        commentService.addComment(authUser, cardId, addcommentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글 작성 완료");
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<String> updateComment(@AuthenticationPrincipal AuthUser authUser,
                                                @PathVariable("id") long id,
                                                @RequestBody UpdateCommentRequestDto updatecommentRequestDto) {
        commentService.updateComment(authUser, id, updatecommentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정 완료");
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal AuthUser authUser,
                                                @PathVariable("id") long id) {
        commentService.deleteComment(authUser, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글 삭제 완료");
    }

}
