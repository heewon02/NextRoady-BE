package com.yoajung.jobplanner.user.controller;

import com.yoajung.jobplanner.common.constant.Constant;
import com.yoajung.jobplanner.common.response.BaseResponseBody;
import com.yoajung.jobplanner.common.status.enums.SuccessStatus;
import com.yoajung.jobplanner.jwt.JwtToken;
import com.yoajung.jobplanner.jwt.service.JwtService;
import com.yoajung.jobplanner.security.userdetails.AuthenticatedUserInfo;
import com.yoajung.jobplanner.user.domain.UserInfoEntity;
import com.yoajung.jobplanner.user.dto.UserInfoSignUpDTO;
import com.yoajung.jobplanner.user.dto.UserInfoResponseDTO;
import com.yoajung.jobplanner.user.mapper.UserInfoEntityMapper;
import com.yoajung.jobplanner.user.service.UserInfoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final JwtService jwtService;

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponseBody<Void>> signUp(
            @Valid @RequestBody UserInfoSignUpDTO userInfoSignUpRequestDTO) {
        userInfoService.signUp(userInfoSignUpRequestDTO);
        return ResponseEntity.status(SuccessStatus.OK.getHttpStatus()).body(SuccessStatus.OK.getBaseResponseBody());
    }

    @GetMapping("/sign-in")
    public ResponseEntity<BaseResponseBody<Void>> signIn() {
        return ResponseEntity.status(SuccessStatus.OK.getHttpStatus()).body(SuccessStatus.OK.getBaseResponseBody());
    }

    @GetMapping("/refresh")
    public ResponseEntity<BaseResponseBody<?>> refresh(
            @RequestHeader(name = Constant.HEADER_REFRESH_TOKEN) String refreshToken, HttpServletResponse response) {
        JwtToken jwtToken = jwtService.reGenerateTokenSet(refreshToken);
        response.setHeader(Constant.HEADER_ACCESS_TOKEN, jwtToken.getAccessToken());
        response.setHeader(Constant.HEADER_REFRESH_TOKEN, jwtToken.getRefreshToken());
        return ResponseEntity.status(SuccessStatus.OK.getHttpStatus()).body(SuccessStatus.OK.getBaseResponseBody());
    }

    @GetMapping("/test")
    public ResponseEntity<BaseResponseBody<Void>> testForSecurity() {
        return ResponseEntity.status(SuccessStatus.OK.getHttpStatus()).body(SuccessStatus.OK.getBaseResponseBody());
    }

    @GetMapping("/info")
    public ResponseEntity<BaseResponseBody<UserInfoResponseDTO>> getUserInfo(
            @AuthenticationPrincipal AuthenticatedUserInfo authenticatedUserInfo) {
        UserInfoEntity userInfoEntity = userInfoService.findUserInfoById(authenticatedUserInfo.id());
        return ResponseEntity.status(SuccessStatus.OK.getHttpStatus())
                .body(SuccessStatus.OK.getBaseResponseBody(UserInfoEntityMapper.toUserInfoResponseDTO(userInfoEntity)));
    }
}
