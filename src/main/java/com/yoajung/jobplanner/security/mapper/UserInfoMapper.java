package com.yoajung.jobplanner.security.mapper;

import com.yoajung.jobplanner.security.thirdparty.user.OAuth2Provider;
import com.yoajung.jobplanner.security.thirdparty.user.OAuth2UserInfo;
import com.yoajung.jobplanner.security.userdetails.AuthenticatedUserInfo;
import com.yoajung.jobplanner.signin.user.domain.UserInfoEntity;
import com.yoajung.jobplanner.signin.user.domain.enums.LoginSource;
import com.yoajung.jobplanner.signin.user.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class UserInfoMapper {

    public static AuthenticatedUserInfo toAuthenticatedUserInfo(UserInfoEntity userInfoEntity){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userInfoEntity.getRole().name()));

        return new AuthenticatedUserInfo(
                userInfoEntity.getId(),
                userInfoEntity.getNickName(),
                authorities
        );
    }

    public static UserInfoEntity toUserInfoEntity(OAuth2UserInfo oAuth2UserInfo){
        LoginSource loginSource = getLoginSource(oAuth2UserInfo);
        return new UserInfoEntity(
                oAuth2UserInfo.getEmail(),
                oAuth2UserInfo.getName(),
                "oauth2user1!1!",
                Role.USER,
                null,
                null,
                oAuth2UserInfo.getNickname(),
                oAuth2UserInfo.getProfileImageUrl(),
                loginSource
        );
    }

    public static LoginSource getLoginSource(OAuth2UserInfo oAuth2UserInfo) {
        OAuth2Provider oAuth2Provider = oAuth2UserInfo.getProvider();
        switch (oAuth2Provider){
            case GOOGLE -> {return LoginSource.GOOGLE;}
            case NAVER -> {return LoginSource.NAVER;}
            case KAKAO -> {return LoginSource.KAKAO;}
            default -> throw new RuntimeException("provider 가 존재하지 않습니다.");
        }
    }
}
