package com.sparta.springtrello.domain.workspace.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkspaceRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
