package com.sparta.springtrello.domain.list.service;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.exception.NotFoundException;
import com.sparta.springtrello.domain.list.dto.request.ListCreateDto;
import com.sparta.springtrello.domain.list.dto.request.ListUpdateRequest;
import com.sparta.springtrello.domain.list.dto.response.ListDetailResponse;
import com.sparta.springtrello.domain.list.dto.response.ListResponse;
import com.sparta.springtrello.domain.list.repository.ListRepository;
import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.entity.Board;
import com.sparta.springtrello.entity.ListEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sparta.springtrello.common.exception.ResponseCode.NOT_FOUND_BOARD;
import static com.sparta.springtrello.common.exception.ResponseCode.NOT_FOUND_LIST;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListRepository listRepository;
    private final BoardRepository boardRepository;

    // 리스트 생성
    @Transactional
    public void createList(AuthUser authUser, ListCreateDto request) {
        // 보드 조회
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

        // 리스트 생성 및 저장
        ListEntity newList = ListEntity.builder()
                .title(request.getTitle())
                .listOrder(request.getListOrder())
                .board(board)  // 보드 설정 추가
                .build();

        listRepository.save(newList);
    }


    // 보드 내 리스트 조회
    public ListDetailResponse getListsByBoard(Long boardId) {
        // 보드에 속한 모든 리스트를 조회하고 ListDetailResponse로 변환
        List<ListEntity> lists = listRepository.findAllByBoardIdOrderByListOrder(boardId);
        return (ListDetailResponse) lists.stream()
                .map(ListDetailResponse::from)  // 각 리스트를 ListDetailResponse로 변환
                .collect(Collectors.toList());  // 결과를 List로 수집
    }

    // 리스트 순서 변경
    @Transactional
    public void changeListOrder(Long listId, int newOrder) {
        ListEntity targetList = listRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LIST));

        List<ListEntity> lists = listRepository.findAllByBoardIdOrderByListOrder(targetList.getBoard().getId());

        if (newOrder < 1 || newOrder > lists.size()) {
            throw new IllegalArgumentException("잘못된 순서 값입니다.");
        }

        for (ListEntity list : lists) {
            if (list.getId().equals(listId)) {
                list.setListOrder(newOrder);
            } else if (list.getListOrder() >= newOrder && list.getListOrder() < targetList.getListOrder()) {
                list.setListOrder(list.getListOrder() + 1);
            } else if (list.getListOrder() <= newOrder && list.getListOrder() > targetList.getListOrder()) {
                list.setListOrder(list.getListOrder() - 1);
            }
        }

        listRepository.saveAll(lists);
    }

    // 리스트 수정
    @Transactional
    public ListResponse updateList(Long listId, ListUpdateRequest request) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LIST));

        list.setTitle(request.getTitle());

        return new ListResponse(listRepository.save(list));
    }

    // 리스트 삭제
    @Transactional
    public void deleteList(AuthUser authUser, Long listId) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LIST));

        listRepository.delete(list);
    }
}
