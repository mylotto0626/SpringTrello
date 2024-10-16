package com.sparta.springtrello.domain.workspace.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class WorkspaceResponseDto {

    private final long id;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;

}
