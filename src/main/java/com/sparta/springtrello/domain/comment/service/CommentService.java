package com.sparta.springtrello.domain.comment.service;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.exception.InvalidRequestException;
import com.sparta.springtrello.common.exception.ResponseCode;
import com.sparta.springtrello.common.exception.UnauthorizedException;
import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.domain.card.entity.Card;
import com.sparta.springtrello.domain.card.repository.CardRepository;
import com.sparta.springtrello.domain.comment.dto.request.UpdateCommentRequestDto;
import com.sparta.springtrello.domain.comment.dto.request.addCommentRequestDto;
import com.sparta.springtrello.domain.list.repository.ListRepository;
import com.sparta.springtrello.domain.user.authority.MemberAuthority;
import com.sparta.springtrello.domain.workspace.repository.WorkspaceRepository;
import com.sparta.springtrello.entity.*;
import com.sparta.springtrello.domain.comment.repository.CommentRepository;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;
    private final ListRepository listRepository;


    // 댓글 등록
    @Transactional
    public Comment addComment(AuthUser authUser, long cardId, addCommentRequestDto addcommentRequestDto) {
       Card card = cardRepository.findById(cardId)
               .orElseThrow(() -> new NoSuchElementException(ResponseCode.NOT_FOUND_CARD.getMessage()));

       ListEntity list = listRepository.findById(authUser.getId())
                .orElseThrow(() -> new NoSuchElementException(ResponseCode.NOT_FOUND_LIST.getMessage()));

        Board board = boardRepository.findById(authUser.getId())
                .orElseThrow(() -> new NoSuchElementException(ResponseCode.NOT_FOUND_BOARD.getMessage()));

        Workspace workspace = workspaceRepository.findByIdOrElseThrow(authUser.getId());

        User user = userRepository.findById(authUser.getId())
               .orElseThrow(() -> new NoSuchElementException(ResponseCode.NOT_FOUND_USER.getMessage()));

       if(authUser.getAuthorities().contains(MemberAuthority.READ_ONLY)){
           throw new InvalidRequestException(ResponseCode.INVALID_USER_AUTHORITY.getMessage());
       }

       Comment comment = new Comment(addcommentRequestDto.getContent(), addcommentRequestDto.getEmoji());

        return commentRepository.save(comment);
    }

    // 댓글 수정
    @Transactional
    public Comment updateComment(AuthUser authUser, long id, UpdateCommentRequestDto updatecommentRequestDto) {
        // 댓글 확인
        Comment comment = commentRepository.findByIdOrElseThrow(id);

        if(!authUser.getId().equals(comment.getUser().getId())){
            throw new UnauthorizedException(ResponseCode.INVALID_USER_AUTHORITY);
        }

        comment.update(updatecommentRequestDto.getContent(), updatecommentRequestDto.getEmoji());
        return comment;
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(AuthUser authUser, long id) {
        Comment comment = commentRepository.findByIdOrElseThrow(id);

        if(!authUser.getId().equals(comment.getUser().getId())) {
            throw new UnauthorizedException(ResponseCode.INVALID_USER_AUTHORITY);
        }

        commentRepository.delete(comment);
    }
}
