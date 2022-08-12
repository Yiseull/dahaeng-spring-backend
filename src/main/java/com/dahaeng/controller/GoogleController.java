package com.dahaeng.controller;

import com.dahaeng.biz.ConfigUtils;
import com.dahaeng.biz.GoogleLoginRequest;
import com.dahaeng.biz.GoogleLoginResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
public class GoogleController {

    @Autowired
    private ConfigUtils configUtils;

    // 구글 로그인창 호출
    @GetMapping(value = "/google")
    public ResponseEntity<Object> moveGoogleInitUrl() {
        String authUrl = configUtils.googleInitUrl();
        URI redirectUri = null;
        try {
            redirectUri = new URI(authUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    // 구글 연동정보 조회
    @GetMapping("/index")
    public String googleAuth(Model model, @RequestParam(value = "code") String authCode) {
        //HTTP Request를 위한 RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //Google OAuth Access Token 요청을 위한 파라미터 세팅
        GoogleLoginRequest googleOAuthRequestParam = GoogleLoginRequest
                .builder()
                .clientId(configUtils.getGoogleClientId())
                .clientSecret(configUtils.getGoogleSecret())
                .code(authCode)
                .redirectUri(configUtils.getGoogleRedirectUri())
                .grantType("authorization_code").build();

        try {
            //JSON 파싱을 위한 기본값 세팅
            //요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

            //AccessToken 발급 요청
            ResponseEntity<String> resultEntity = restTemplate.postForEntity(configUtils.getGoogleAuthUrl() + "/token", googleOAuthRequestParam, String.class);

            //Token Request
            GoogleLoginResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleLoginResponse>() {
            });

            //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
            String jwtToken = result.getIdToken();
            String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
                    .queryParam("id_token", jwtToken).encode().toUriString();

            String resultJson = restTemplate.getForObject(requestUrl, String.class);

            Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});
            model.addAllAttributes(userInfo);
            model.addAttribute("token", result.getAccessToken());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}

