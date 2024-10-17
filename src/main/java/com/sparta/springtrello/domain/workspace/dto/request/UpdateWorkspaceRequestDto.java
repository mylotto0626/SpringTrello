package com.sparta.springtrello.domain.workspace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkspaceRequestDto {

    private String name;
    private String description;
}
