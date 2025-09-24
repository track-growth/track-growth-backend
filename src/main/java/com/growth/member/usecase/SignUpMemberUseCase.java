package com.growth.member.usecase;

import com.growth.member.dto.request.SignUpMemberRequestDto;
import com.growth.member.dto.response.SignUpMemberResponseDto;

public interface SignUpMemberUseCase {
    SignUpMemberResponseDto signUp(SignUpMemberRequestDto requestDto);
}
