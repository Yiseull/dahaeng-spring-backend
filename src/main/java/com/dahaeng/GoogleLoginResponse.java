package com.dahaeng;

import lombok.Data;
import lombok.NoArgsConstructor;

// 일회성 토큰을 통해 얻은 Response VO
// idToken을 전달해 사용자 정보를 얻을 예정

@Data
@NoArgsConstructor
public class GoogleLoginResponse {
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String scope;
    private String tokenType;
    private String idToken;
}
