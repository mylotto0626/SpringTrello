package com.sparta.springtrello.domain.workspace.service;

import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.exception.ResponseCode;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.authority.MemberAuthority;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.domain.workspace.dto.request.CreateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.InviteMemberRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.UpdateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.response.WorkspaceResponseDto;
import com.sparta.springtrello.domain.workspace.entity.Workspace;
import com.sparta.springtrello.domain.workspace.repository.WorkspaceRepository;
import com.sparta.springtrello.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createWorkspace(AuthUser authUser, CreateWorkspaceRequestDto createWorkspaceRequestDto) {

        User user = userRepository.findById(authUser.getId()).orElseThrow(
                () -> new NoSuchElementException(ResponseCode.NOT_FOUND_USER.getMessage()));

        // ADMIN 검증
        if(!authUser.getAuthorities().contains(Authority.ROLE_ADMIN)){
            throw new AccessDeniedException(ResponseCode.INVALID_USER_AUTHORITY.getMessage());
        }

        Workspace workspace = new Workspace(createWorkspaceRequestDto.getName(),
                createWorkspaceRequestDto.getDescription());

        workspaceRepository.save(workspace);
    }

    @Transactional
    public void inviteMember(AuthUser authuser, long id, InviteMemberRequestDto inviteMemberRequestDto) {
        // 회원 가입한 이메일 대조
        User user = userRepository.findByEmail(inviteMemberRequestDto.getEmail()).orElseThrow(
                () -> new NoSuchElementException(ResponseCode.INVALID_EMAIL.getMessage()));

        Workspace workspace = workspaceRepository.findByIdOrElseThrow(id);

        // 멤버 초대 권한 확인 ( ADMIN OR WORKSPACE )
        if(authuser.getAuthorities().contains(Authority.ROLE_ADMIN) && authuser.getAuthorities()
                                    .contains(MemberAuthority.WORKSPACE)){
            throw new AccessDeniedException(ResponseCode.INVALID_USER_AUTHORITY.getMessage());
        }

        workspace.getMembers().add(user);
        workspaceRepository.save(workspace);
    }

    public List<WorkspaceResponseDto> getWorkspace(long userId) {
        List<Workspace> workspaces = workspaceRepository.findByMembersId(userId);

        return workspaces.stream().map(workspace -> new WorkspaceResponseDto(workspace.getId()
                ,workspace.getName(),workspace.getDescription(),workspace.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateWorkspace(AuthUser authUser, long id, UpdateWorkspaceRequestDto updateWorkspaceRequestDto) {
        Workspace workspace = workspaceRepository.findByIdOrElseThrow(id);

        if(!authUser.getAuthorities().equals(MemberAuthority.WORKSPACE)){
            throw new AccessDeniedException(ResponseCode.INVALID_USER_AUTHORITY.getMessage());
        }

        workspace.update(updateWorkspaceRequestDto.getName(), updateWorkspaceRequestDto.getDescription());
    }

    @Transactional
    public void deleteWorkspace(AuthUser authUser, Long id) {
        Workspace workspace = workspaceRepository.findByIdOrElseThrow(id);

        if(!authUser.getAuthorities().equals(MemberAuthority.WORKSPACE)){
            throw new AccessDeniedException(ResponseCode.INVALID_USER_AUTHORITY.getMessage());
        }

        workspaceRepository.deleteById(id);
    }
}
