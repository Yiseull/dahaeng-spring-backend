package com.dahaeng.controller;

import com.dahaeng.biz.naver.NaverLoginBO;
import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "*")
public class NaverController {

    @Autowired
    private UserService userService;

    /* NaverLoginBO */
    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;

    //로그인 첫 화면 요청 메소드
    @RequestMapping(value = "/getNaverAuth")
    public ResponseEntity<JSONObject> login(HttpSession session) throws UnsupportedEncodingException {
        ResponseEntity<JSONObject> entity = null;

        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String apiURL = naverLoginBO.getAuthorizationUrl(session);

        JSONObject obj = new JSONObject();
        obj.put("apiURL", apiURL);
        entity = new ResponseEntity<>(obj, HttpStatus.OK);

        return entity;
    }


    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/naver")
    public ResponseEntity<UserVO> callback(@RequestParam String code, @RequestParam String state, HttpSession session)
            throws Exception {
        ResponseEntity<UserVO> entity = null;

        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj;

        jsonObj = (JSONObject) jsonParser.parse(apiResult);
        JSONObject response_obj = (JSONObject) jsonObj.get("response");

        // 프로필 조회
        String email = (String) response_obj.get("email");

        UserVO user = null;
        try {
            user = userService.findByEmail(email);

            // 등록된 회원이 아니면
            if (user == null) {
                user.setEmail(email);
            }
        } catch(Exception e) {
            e.printStackTrace();

        }

        entity = new ResponseEntity<>(user, HttpStatus.OK);
        return entity;
    }
}