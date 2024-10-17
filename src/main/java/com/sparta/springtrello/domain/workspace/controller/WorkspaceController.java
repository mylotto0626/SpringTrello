package com.sparta.springtrello.domain.workspace.controller;

import com.sparta.springtrello.common.annotation.Auth;
import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.response.SuccessResponse;
import com.sparta.springtrello.domain.workspace.dto.request.CreateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.InviteMemberRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.UpdateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.response.WorkspaceResponseDto;
import com.sparta.springtrello.domain.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    // 워크 스페이스 생성
    @PostMapping("/workspaces")
    public ResponseEntity<String> createWorkspace(@Auth AuthUser authUser,
                                                  @RequestBody CreateWorkspaceRequestDto createWorkspaceRequestDto)
    {
       workspaceService.createWorkspace(authUser, createWorkspaceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("워크스페이스 생성 완료");
    }

    // 워크 스페이스 멤버 초대
    @PostMapping("/workspaces/{id}/invite")
    public ResponseEntity<String> inviteMember(@AuthenticationPrincipal AuthUser authUser,
                                               @PathVariable long id,
                                               @RequestBody InviteMemberRequestDto inviteMemberRequestDto){
        workspaceService.inviteMember(authUser, id, inviteMemberRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("초대 완료");
    }

    // 워크 스페이스 조회
    @GetMapping("/workspaces")
    public ResponseEntity<List<WorkspaceResponseDto>> getWorkspace(@Auth AuthUser authUser){
        List<WorkspaceResponseDto> workspaceResponseDtos = workspaceService.getWorkspace(authUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(workspaceResponseDtos);
    }

    // 워크 스페이스 수정
    @PutMapping("/workspaces/{id}")
    public ResponseEntity<String> updateWorkspace(@AuthenticationPrincipal AuthUser authUser,
                                                  @PathVariable long id,
                                                  @RequestBody UpdateWorkspaceRequestDto updateRequestDto){
        workspaceService.updateWorkspace(authUser, id, updateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("수정 완료");
    }

    // 워크 스페이스 삭제
    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<String> deleteWorkspace(@AuthenticationPrincipal AuthUser authUser,
                                                  @PathVariable long id){
        workspaceService.deleteWorkspace(authUser, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제 완료");
    }
}
