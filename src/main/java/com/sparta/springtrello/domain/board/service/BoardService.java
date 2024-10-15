package com.sparta.springtrello.domain.board.service;

import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.entity.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    public Board createBoard(BoardCreateDto boardCreateDto) {
        if (boardCreateDto.getName() == null || boardCreateDto.isEmpty()) {
            throw new IllegalArgumentException("보드 제목을 입력해주세요.");
        }

        Board board = Board.builder()
                .name(name)
                .createdAt(new Date())
                .modifiedAt(new Date())
                .build();

        return boardRepository.save(board);
    }

    public Board updateBoard(Long boardId, BoardUpdateDto boardUpdateDto) {
        return null;
    }

    public void deleteBoard(Long boardId) {
        return;
    }

    public Board createBoard(String name) {

        // 제목이 비어 있는지 확인


        // 읽기 전용 유저인지 확인
        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 보드를 생성할 수 없습니다.");
        }


    }

    public List<Board> getBoardsByUser(Long userId) {
        return boardRepository.findBoardsByWorkspaceAndUser(null, userId);  // Workspace를 적용할 경우 수정
    }

    public Board getBoardById(Long id, Long userId) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));
    }

    public void deleteBoard(Long id, Long userId) {
        Board board = getBoardById(id, userId);
        User user = board.getUser();

        // 읽기 전용 유저인지 확인
        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 보드를 삭제할 수 없습니다.");
        }

        boardRepository.delete(board);
    }



}
