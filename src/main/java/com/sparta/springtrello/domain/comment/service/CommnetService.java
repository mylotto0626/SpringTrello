package com.sparta.springtrello.domain.comment.service;

import com.sparta.springtrello.domain.comment.repository.CommnetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommnetService {
    private final CommnetRepository commnetRepository;


}
