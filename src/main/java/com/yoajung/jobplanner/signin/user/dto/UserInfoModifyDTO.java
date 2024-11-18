package com.yoajung.jobplanner.signin.user.dto;

import com.yoajung.jobplanner.signin.user.domain.enums.Gender;
import com.yoajung.jobplanner.signin.user.domain.enums.LoginSource;
import com.yoajung.jobplanner.signin.user.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserInfoModifyDTO(
        @NotBlank(message = "이메일을 입력해야 합니다.")
        String email,

        @NotBlank(message = "사용자 이름을 입력해야 합니다.")
        String username,

        @NotNull(message = "역할을 부여해야 합니다.")
        Role role,

        @NotNull(message = "성별을 입력해야 합니다.")
        Gender gender,

        @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
        String phoneNumber,

        @NotBlank(message = "닉네임을 입력해야 합니다.")
        String nickName,

        @NotNull(message = "로그인 소스를 부여해야 합니다.")
        LoginSource loginSource
) {
}
