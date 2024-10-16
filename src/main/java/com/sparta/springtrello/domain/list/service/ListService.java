package com.sparta.springtrello.domain.list.service;

import com.sparta.springtrello.common.enums.Authority;
import com.sparta.springtrello.common.exception.NotFoundException;
import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.domain.list.dto.request.ListCreateDto;
import com.sparta.springtrello.domain.list.dto.request.ListUpdateRequest;
import com.sparta.springtrello.domain.list.dto.response.ListDetailResponse;
import com.sparta.springtrello.domain.list.dto.response.ListResponse;
import com.sparta.springtrello.domain.list.repository.ListRepository;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.entity.Board;
import com.sparta.springtrello.entity.ListEntity;
import com.sparta.springtrello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.sparta.springtrello.common.exception.ResponseCode.NOT_FOUND_BOARD;

@Service
public class ListService {
    private final ListRepository listRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public ListService(ListRepository listRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    // 리스트 생성
    public ListResponse createList(Long userId, Long boardId, ListCreateDto listCreateDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("로그인 필요")).getUser();
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));

        // 읽기 전용 유저 확인
        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 리스트를 생성할 수 없습니다.");
        }

        ListEntity newList = ListEntity.from(user, listCreateDto);
        ListEntity savedList = listRepository.save(newList);

        return ListResponse.from(savedList);

    }

    // 보드 내 리스트 전체 조회
    public ListDetailResponse getListsByBoard(Long boardId) {
        ListEntity lists = listRepository.findAllByBoardId(boardId);
        return ListDetailResponse.from(lists);
    }

    // 리스트 순서 변경
    public void changeListOrder(Long listId, int newOrder) {
        // 리스트와 보드 검증
        ListEntity targetList = listRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("리스트를 찾을 수 없습니다."));
        Board board = targetList.getBoard();

        // 현재 보드의 모든 리스트 가져오기
        List<ListEntity> lists = listRepository.findByBoardIdOrderByListOrder(board.getId());

        // 새로운 순서로 조정
        if (newOrder < 1 || newOrder > lists.size()) {
            throw new IllegalArgumentException("잘못된 순서 값입니다.");
        }

        // 리스트 순서 재배치
        for (ListEntity list : lists) {
            if (list.getId().equals(listId)) {
                list.setListOrder(newOrder); // 대상 리스트의 새로운 순서 설정
            } else {
                // 대상 리스트 이전의 순서들을 조정
                if (list.getListOrder() >= newOrder && list.getListOrder() < targetList.getListOrder()) {
                    list.setListOrder(list.getListOrder() + 1);
                }
                // 대상 리스트 이후의 순서들을 조정
                else if (list.getListOrder() <= newOrder && list.getListOrder() > targetList.getListOrder()) {
                    list.setListOrder(list.getListOrder() - 1);
                }
            }
        }

        // 리스트 저장
        listRepository.saveAll(lists);
    }


    // 리스트 수정
    public ListEntity updateList(Long boardId, ListUpdateRequest request, Long userId) {
        ListEntity list = listRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

        User user = list.getBoard().getUser();

        if (!user.getId().equals(userId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 리스트를 수정할 수 없습니다.");
        }

        list.setTitle(request.getTitle());
        list.setModifiedAt(LocalDateTime.now());

        return listRepository.save(list);
    }

    // 리스트 삭제
    public void deleteList(Long listId, Long userId) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

        User user = list.getBoard().getUser();

        if (!user.getId().equals(userId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        if (user.getAuthority().equals(Authority.READ_ONLY)) {
            throw new IllegalArgumentException("읽기 전용 권한으로는 리스트를 삭제할 수 없습니다.");
        }

        listRepository.delete(list);
    }
}
