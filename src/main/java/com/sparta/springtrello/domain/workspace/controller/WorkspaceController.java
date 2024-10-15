package com.sparta.springtrello.domain.workspace.controller;

import com.sparta.springtrello.domain.workspace.dto.request.CreateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.createWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.request.updateWorkspaceRequestDto;
import com.sparta.springtrello.domain.workspace.dto.response.WorkspaceResponseDto;
import com.sparta.springtrello.domain.workspace.entity.Workspace;
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
    public ResponseEntity<String> createWorkspace(@AuthenticationPrincipal AuthUser authUser,
                                                  @RequestBody CreateWorkspaceRequestDto craterequestDto)
    {
        workspaceService.createWorkspace(authUser, craterequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("워크스페이스 생성 완료");
    }

    // 워크 스페이스 조회
    @GetMapping("/workspaces/{memberid}")
    public ResponseEntity<List<WorkspaceResponseDto>> GetWorkspace(@PathVariable("memberid") long memberId){
        List<WorkspaceResponseDto> workspaceResponseDtos = workspaceService.GetWorkspace(memberId);
        return ResponseEntity.status(HttpStatus.OK).body("조회 완료");
    }

    // 워크 스페이스 스정
    @PutMapping("/workspaces/{id}")
    public ResponseEntity<String> updateWorkspace(@AuthenticationPrincipal AuthUser authUser,
                                                  @PathVariable long id, @RequestBody updateWorkspaceRequestDto updaterequest){
        workspaceService.updateWorkspace(authUser, id, updaterequest);
        ResponseEntity.status(HttpStatus.OK).body("수정 완료");
    }

    // 워크 스페이스 삭제
    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<String> deleteWorkspace(@AuthenticationPrincipal AuthUser authUser, 
                                                  @PathVariable long id){
        workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제완료");
    }

}
