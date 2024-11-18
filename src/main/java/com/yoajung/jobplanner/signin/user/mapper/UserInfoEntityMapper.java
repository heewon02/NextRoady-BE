package com.yoajung.jobplanner.signin.user.mapper;

import com.yoajung.jobplanner.signin.user.domain.UserInfoEntity;
import com.yoajung.jobplanner.signin.user.dto.UserInfoModifyDTO;
import com.yoajung.jobplanner.signin.user.dto.UserInfoResponseDTO;
import com.yoajung.jobplanner.signin.user.dto.UserInfoSignUpDTO;

public class UserInfoEntityMapper {
    public static UserInfoEntity toUserInfo(UserInfoResponseDTO userInfoResponseDTO) {
        return new UserInfoEntity(
                userInfoResponseDTO.email(),
                userInfoResponseDTO.username(),
                null,
                userInfoResponseDTO.role(),
                userInfoResponseDTO.gender(),
                userInfoResponseDTO.phoneNumber(),
                userInfoResponseDTO.nickName(),
                null,
                userInfoResponseDTO.loginSource()
        );
    }

    public static UserInfoEntity toUserInfo(UserInfoSignUpDTO userInfoSignUpRequestDTO) {
        return new UserInfoEntity(
                userInfoSignUpRequestDTO.email(),
                userInfoSignUpRequestDTO.username(),
                userInfoSignUpRequestDTO.password(),
                userInfoSignUpRequestDTO.role(),
                userInfoSignUpRequestDTO.gender(),
                userInfoSignUpRequestDTO.phoneNumber(),
                userInfoSignUpRequestDTO.nickName(),
                null,
                userInfoSignUpRequestDTO.loginSource()
        );
    }

    public static UserInfoResponseDTO toUserInfoResponseDTO(UserInfoEntity userInfoEntity) {
        return new UserInfoResponseDTO(
                userInfoEntity.getEmail(),
                userInfoEntity.getUsername(),
                userInfoEntity.getRole(),
                userInfoEntity.getGender(),
                userInfoEntity.getPhoneNumber(),
                userInfoEntity.getNickName(),
                userInfoEntity.getLoginSource()
        );
    }

    public static UserInfoSignUpDTO toUserInfoRequestDTO(UserInfoEntity userInfoEntity) {
        return new UserInfoSignUpDTO(
                userInfoEntity.getEmail(),
                userInfoEntity.getUsername(),
                userInfoEntity.getPassword(),
                userInfoEntity.getRole(),
                userInfoEntity.getGender(),
                userInfoEntity.getPhoneNumber(),
                userInfoEntity.getNickName(),
                userInfoEntity.getLoginSource()
        );
    }

    public static UserInfoEntity toUserInfoEntity(UserInfoEntity userInfoEntity, UserInfoModifyDTO userInfoModifyDTO) {
        return new UserInfoEntity(
                userInfoEntity.getId(),
                userInfoEntity.getCreateTime(),
                userInfoEntity.getModifiedTime(),
                userInfoEntity.getDeletedTime(),
                userInfoEntity.getState(),
                userInfoEntity.getEmail(),
                userInfoModifyDTO.username(),
                userInfoEntity.getPassword(),
                userInfoModifyDTO.role(),
                userInfoModifyDTO.gender(),
                userInfoModifyDTO.phoneNumber(),
                userInfoModifyDTO.nickName(),
                null,
                userInfoModifyDTO.loginSource()
        );
    }

    public static UserInfoModifyDTO toUserInfoModifyDTO(UserInfoEntity userInfoEntity) {
        return new UserInfoModifyDTO(
                userInfoEntity.getEmail(),
                userInfoEntity.getUsername(),
                userInfoEntity.getRole(),
                userInfoEntity.getGender(),
                userInfoEntity.getPhoneNumber(),
                userInfoEntity.getNickName(),
                userInfoEntity.getLoginSource()
        );
    }
}
