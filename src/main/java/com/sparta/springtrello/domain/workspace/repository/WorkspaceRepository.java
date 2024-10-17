package com.sparta.springtrello.domain.workspace.repository;

import com.sparta.springtrello.common.exception.ResponseCode;
import com.sparta.springtrello.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    default Workspace findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException(ResponseCode.NOT_FOUND_USER.getMessage()));
    }

    List<Workspace> findByMembersId(Long userId);
}
