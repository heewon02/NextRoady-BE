package com.yoajung.jobplanner.user.dto;

import com.yoajung.jobplanner.user.domain.enums.Gender;
import com.yoajung.jobplanner.user.domain.enums.LoginSource;
import com.yoajung.jobplanner.user.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserInfoResponseDTO(
        @NotBlank
        String email,

        @NotBlank
        String username,

        Role role,

        Gender gender,

        String phoneNumber,

        String address,

        String nickName,

        @NotNull
        LoginSource loginSource){
}

