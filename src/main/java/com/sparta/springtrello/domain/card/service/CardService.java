package com.sparta.springtrello.domain.card.service;

import com.sparta.springtrello.common.exception.InvalidRequestException;
import com.sparta.springtrello.domain.board.repository.BoardRepository;
import com.sparta.springtrello.domain.card.dto.CardRequestDto;
import com.sparta.springtrello.domain.card.dto.CardResponseDto;
import com.sparta.springtrello.domain.card.entity.Card;
import com.sparta.springtrello.domain.card.repository.CardRepository;
import com.sparta.springtrello.domain.list.repository.ListRepository;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.authority.MemberAuthority;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.domain.workspace.repository.WorkspaceRepository;
import com.sparta.springtrello.entity.User;
import com.sparta.springtrello.entity.Workspace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final BoardRepository boardRepository;
    private final ListRepository listRepository;
    private final CardRepository cardRepository;

    // 카드 생성
    @Transactional
    public CardResponseDto createCard(CardRequestDto requestDto, Long userId, Long workspaceId, Long boardId, Long listId) {

        // 사용자 권한 검사
        User user = checkUserExist(userId);
        validateUserAuthority(user.getAuthority());

        // 워크스페이스 멤버 권한 검사
        Workspace workspace = checkWorkspaceExist(workspaceId);
        validateMemberAuthority(user.getMemberAuthority());

        // 리스트 존재 여부 확인
        com.sparta.springtrello.entity.ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리스트가 존재하지 않습니다."));

        // 카드 생성 및 저장
        Card card = new Card(requestDto, list);
        Card createdCard = cardRepository.save(card);

        return new CardResponseDto(createdCard);
    }

    // 카드 수정
    @Transactional
    public CardResponseDto updateCard(CardRequestDto requestDto, Long userId, Long workspaceId, Long boardId, Long listId, Long cardId) {
        // 사용자 권한 및 카드 존재 유무 검사
        User user = checkUserExist(userId);
        validateUserAuthority(user.getAuthority());

        Card card = checkCardExist(cardId);

        // 워크스페이스 멤버 권한 검사
        validateMemberAuthority(user.getMemberAuthority());

        // 카드 정보 업데이트
        card.updateCard(requestDto.getTitle(), requestDto.getContents(), requestDto.getDueDate());
        return new CardResponseDto(card);
    }

    // 카드 상세 조회
    public CardResponseDto getCardDetail(Long userId, Long workspaceId, Long boardId, Long listId, Long cardId) {
        User user = checkUserExist(userId);

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        return new CardResponseDto(card);
    }

    // 카드 삭제
    @Transactional
    public void deleteCard(Long userId, Long workspaceId, Long boardId, Long listId, Long cardId) {
        // 사용자 권한 및 카드 존재 유무 검사
        User user = checkUserExist(userId);
        validateUserAuthority(user.getAuthority());

        Card card = checkCardExist(cardId);

        // 워크스페이스 멤버 권한 검사
        validateMemberAuthority(user.getMemberAuthority());

        // 카드 삭제
        cardRepository.deleteById(card.getId());
    }

    // 카드 검색
    public Page<CardResponseDto> searchCards(Long workspaceId, Long boardId, Long listId,
                                             String title, String contents, String dueDate, String assigneeName,
                                             Long userId, Pageable pageable) {
        // 검색 조건에 맞는 카드 조회
        Page<Card> cards = cardRepository.searchCards(listId, userId, title, contents, dueDate, assigneeName, pageable);


        return cards.map(CardResponseDto::new);
    }

    // 카드 존재 여부
    private Card checkCardExist(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new InvalidRequestException("해당 카드가 없습니다"));
    }

    // 사용자 존재 여부 확인
    private User checkUserExist(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new InvalidRequestException("해당 사용자가 존재하지 않습니다."));
    }

    // 워크스페이스 존재 여부 확인
    private Workspace checkWorkspaceExist(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new InvalidRequestException("해당 워크스페이스가 존재하지 않습니다."));
    }

    // 사용자 권한 확인 (전체 시스템 권한)
    private void validateUserAuthority(Authority authority) {
        if (authority == Authority.ROLE_READ) {
            throw new InvalidRequestException("읽기 전용 권한으로는 해당 작업을 수행할 수 없습니다.");
        }
    }

    // 멤버 권한 확인 (워크스페이스 내에서의 권한)
    private void validateMemberAuthority(MemberAuthority memberAuthority) {
        if (memberAuthority == MemberAuthority.READ_ONLY) {
            throw new InvalidRequestException("읽기 전용 멤버는 해당 작업을 수행할 수 없습니다.");
        }
    }
}
