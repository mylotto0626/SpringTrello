package com.sparta.springtrello.domain.workspace.dto.response;

import com.sparta.springtrello.entity.Workspace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class WorkspaceResponseDto {

    private final long id;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;

    public WorkspaceResponseDto(Workspace createWorkspace) {
        this.id = createWorkspace.getId();
        this.name = createWorkspace.getName();
        this.description = createWorkspace.getDescription();
        this.createdAt = createWorkspace.getCreatedAt();
    }
}
