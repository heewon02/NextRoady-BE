package com.yoajung.jobplanner.user.controller;

import com.yoajung.jobplanner.common.response.BaseResponseBody;
import com.yoajung.jobplanner.common.status.enums.SuccessStatus;
import com.yoajung.jobplanner.security.userdetails.AuthenticatedUserInfo;
import com.yoajung.jobplanner.user.domain.UserInfoEntity;
import com.yoajung.jobplanner.user.domain.enums.LoginSource;
import com.yoajung.jobplanner.user.dto.UserInfoModifyDTO;
import com.yoajung.jobplanner.user.dto.UserInfoResponseDTO;
import com.yoajung.jobplanner.user.mapper.UserInfoEntityMapper;
import com.yoajung.jobplanner.user.service.UserInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
@RestController
public class OAuth2Controller {
    private final UserInfoService userInfoService;

    @GetMapping("/sign-up")
    public ResponseEntity<BaseResponseBody<UserInfoResponseDTO>> getSignUpInfo(Authentication authentication){
        UserInfoEntity userInfoEntity = userInfoService.findUserInfoById(getIdFromAuthentication(authentication));
        if(userInfoEntity.getLoginSource() == LoginSource.THIS) throw new RuntimeException("해당 사용자는 OAuth2.0 사용자가 아닙니다.");
        return ResponseEntity
                .status(SuccessStatus.OK.getHttpStatus())
                .body(SuccessStatus.OK.getBaseResponseBody(UserInfoEntityMapper.toUserInfoResponseDTO(userInfoEntity)));
    }

    @PatchMapping("/sign-up")
    public ResponseEntity<BaseResponseBody<?>> signUp(@Valid @RequestBody UserInfoModifyDTO userInfoModifyDTO, Authentication authentication){
        userInfoService.modifyUserInfo(userInfoModifyDTO, getIdFromAuthentication(authentication));
        return ResponseEntity.status(SuccessStatus.OK.getHttpStatus()).body(SuccessStatus.OK.getBaseResponseBody());
    }

    public Long getIdFromAuthentication(Authentication authentication) {
        return ((AuthenticatedUserInfo) authentication.getPrincipal()).id();
    }
}
