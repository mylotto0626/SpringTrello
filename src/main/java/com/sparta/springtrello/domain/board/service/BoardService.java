package com.sparta.springtrello.domain.board.service;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.exception.NotFoundException;
import com.sparta.springtrello.common.exception.UnauthorizedException;
import com.sparta.springtrello.domain.board.dto.request.BoardCreateDto;
import com.sparta.springtrello.domain.board.dto.request.BoardUpdateRequest;
import com.sparta.springtrello.domain.board.dto.response.BoardResponse;
import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.entity.Board;
import com.sparta.springtrello.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.sparta.springtrello.common.exception.ResponseCode.*;

@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // 보드 생성
    @Transactional
    public BoardResponse createBoard(AuthUser authUser, BoardCreateDto boardCreateDto) {

        User user = userRepository.findById(authUser.getId()).orElseThrow(() ->
                new NotFoundException(NOT_FOUND_USER));

        Board newBoard = Board.from(user, boardCreateDto);
        Board savedBoard = boardRepository.save(newBoard);

        return BoardResponse.from(savedBoard);
    }


    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new NotFoundException(NOT_FOUND_BOARD));
    }


    public Page<BoardResponse> getBoards(String name, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Board> boards = null;
        
        return boards.map(store -> {
            return BoardResponse.from(store);
        });

    }

    @Transactional
    public Board updateBoard(Long boardId, BoardUpdateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

        board.setName(request.getName());
        board.setModifiedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

        boardRepository.delete(board);
    }
}