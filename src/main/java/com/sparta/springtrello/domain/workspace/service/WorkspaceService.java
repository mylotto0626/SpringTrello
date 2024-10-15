package com.sparta.springtrello.domain.workspace.service;

import com.sparta.springtrello.domain.workspace.dto.request.CreateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.updateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.response.WorkspaceResponseDto;
import com.sparta.springtrello.domain.workspace.entity.Workspace;
import com.sparta.springtrello.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    @Transactional
    public void createWorkspace(AuthUser authUser, CreateWorkspaceRequestDto createWorkspaceRequestDto) {

        if(authUser.getUserRole() != UserRole.ADMIN){
            throw new AccessDeniedException("계정 권한이 존재하지 않습니다.");
        }

        Workspace workspace = new Workspace(createWorkspaceRequestDto.getName(),
                createWorkspaceRequestDto.getDescription());

        workspaceRepository.save(workspace);
    }

    public List<WorkspaceResponseDto> GetWorkspace(Long memberId) {
        List<Workspace> workspaces = userRepository.findById(memberId);

    }

    @Transactional
    public void updateWorkspace(AuthUser authUser, long id, updateWorkspaceRequestDto updaterequest) {
        Workspace workspace = workspaceRepository.findByIdOrElseThrow(id);
        workspace.update(updaterequest.getName(), updaterequest.getDescription());
    }


    @Transactional
    public void deleteWorkspace(Long id) {
        workspaceRepository.deleteById(id);

        if()
    }


}
