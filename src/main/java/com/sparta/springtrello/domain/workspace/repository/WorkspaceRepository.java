package com.sparta.springtrello.domain.workspace.repository;

import com.sparta.springtrello.domain.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.NoSuchElementException;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    default Workspace findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }
}
