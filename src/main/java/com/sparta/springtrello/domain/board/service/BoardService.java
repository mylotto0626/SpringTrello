package com.sparta.springtrello.domain.board.service;

import com.sparta.springtrello.common.exception.NotFoundException;
import com.sparta.springtrello.common.exception.UnauthorizedException;
import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import com.sparta.springtrello.domain.board.dto.request.BoardUpdateRequest;
import com.sparta.springtrello.domain.board.dto.response.BoardResponse;
import com.sparta.springtrello.domain.board.dto.response.BoardUpdateResponse;
import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.entity.Board;
import com.sparta.springtrello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sparta.springtrello.common.exception.ResponseCode.*;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // 보드 생성
    public BoardResponse createBoard(Long userId, BoardCreateDto boardCreateDto) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(NOT_FOUND_USER));

        Board newBoard = Board.from(user, boardCreateDto);
        Board savedStore = boardRepository.save(newBoard);

        return BoardResponse.from(savedStore);
    }


    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new NotFoundException(NOT_FOUND_BOARD));
    }


    public Page<BoardResponse> getBoards(String name, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Board> boards;

        if (name == null || name.isEmpty()) {
            boards = boardRepository.findByDeletedAtIsNull(pageable);
        } else {
            boards = boardRepository.findByNameContainingAndDeletedAtIsNull(name, pageable);
        }

        return boards.map(store -> {
            return BoardResponse.from(store);
        });

    }


    public Board updateBoard(Long boardId, BoardUpdateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

        board.setName(request.getName());
        board.setModifiedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    public void deleteBoard(Long boardId, String username) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));
        if (!board.getUser().getNickName().equals(username)) {
            throw new UnauthorizedException(INVALID_USER_AUTHORITY);
        }
        boardRepository.delete(board);
    }
}