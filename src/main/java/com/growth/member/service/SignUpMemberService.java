package com.growth.member.service;

import com.growth.member.dto.request.SignUpMemberRequestDto;
import com.growth.member.dto.response.SignUpMemberResponseDto;
import com.growth.member.usecase.SignUpMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SignUpMemberService implements SignUpMemberUseCase {

    @Override
    public SignUpMemberResponseDto signUp(SignUpMemberRequestDto requestDto) {
        return null;
    }
}
