package com.dahaeng.controller;

import com.dahaeng.biz.naver.NaverLoginBO;
import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/naver")
public class NaverController {

    @Autowired
    private UserService userService;

    /* NaverLoginBO */
    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;

    @RequestMapping(value = "/callback")
    public ResponseEntity<JSONObject> callback(@RequestBody Map<String, Object> param) throws Exception {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        String code = (String) param.get("code");
        String state = (String) param.get("state");

        // Access Token 발급받기
        String accessTokenResponse = naverLoginBO.getAccessToken(code, state);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj;
        jsonObj = (JSONObject) jsonParser.parse(accessTokenResponse);
        String accessToken = (String) jsonObj.get("access_token");

        obj.put("access_token", accessToken);

        // 사용자 허용 프로필 조회
        String profileResponse = naverLoginBO.getUserProfile(accessToken);

        JSONObject jsonObj2;
        jsonObj2 = (JSONObject) jsonParser.parse(profileResponse);
        JSONObject response_obj = (JSONObject) jsonObj2.get("response");
        String email = (String) response_obj.get("email");

        // 값이 제대로 넘어왔는지 확인
        System.out.println("accessToken = " + accessToken);
        System.out.println("response = " + response_obj);
        System.out.println("email = " + email);

        UserVO user = null;
        try {
            user = userService.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 등록된 회원이 아니면
        if (user == null) {
            user.setEmail(email);
        }
        obj.put("user", user);
        entity = new ResponseEntity<>(obj, HttpStatus.OK);

        return entity;
    }

    //네이버로 회원가입
    @RequestMapping(value = "/signin")
    public ResponseEntity<JSONObject> singin(@RequestBody Map<String, Object> param) throws Exception {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        String code = (String) param.get("code");
        String state = (String) param.get("state");
        String nickname = (String) param.get("nickname");

        // Access Token 발급받기
        String accessTokenResponse = naverLoginBO.getAccessToken(code, state);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj;
        jsonObj = (JSONObject) jsonParser.parse(accessTokenResponse);
        String accessToken = (String) jsonObj.get("access_token");

        obj.put("access_token", accessToken);

        // 사용자 허용 프로필 조회
        String profileResponse = naverLoginBO.getUserProfile(accessToken);

        JSONObject jsonObj2;
        jsonObj2 = (JSONObject) jsonParser.parse(profileResponse);
        JSONObject response_obj = (JSONObject) jsonObj2.get("response");
        String email = (String) response_obj.get("email");

        UserVO vo = new UserVO();
        vo.setEmail(email);
        vo.setNickname(nickname);
        vo.setPassword("");

        UserVO user = null;
        try {
            userService.insertUser(vo);
            user = userService.findByEmail(vo.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        obj.put("user", user);
        entity = new ResponseEntity<>(obj, HttpStatus.OK);

        return entity;
    }
}
