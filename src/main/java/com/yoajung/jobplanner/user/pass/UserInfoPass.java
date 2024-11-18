package com.yoajung.jobplanner.user.pass;

import com.yoajung.jobplanner.user.domain.enums.Gender;
import com.yoajung.jobplanner.user.domain.enums.LoginSource;
import com.yoajung.jobplanner.user.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserInfoPass(
        @NotBlank
        String email,

        @NotBlank
        String username,

        @NotNull
        Role role,

        @NotNull
        Gender gender,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String address,

        @NotBlank
        String nickName,

        @NotBlank
        String imageUrl,

        @NotNull
        LoginSource loginSource
) {
}
