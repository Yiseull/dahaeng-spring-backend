package com.dahaeng.controller;

import com.dahaeng.biz.naver.NaverLoginBO;
import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NaverController {

    @Autowired
    private UserService userService;

    /* NaverLoginBO */
    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;

    //로그인 첫 화면 요청 메소드
    @RequestMapping(value = "/naverLogin")
    public String login(Model model, HttpSession session) throws UnsupportedEncodingException {

        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String apiURL = naverLoginBO.getAuthorizationUrl(session);

        // 객체 바인딩
        model.addAttribute("apiURL", apiURL);

        /* 생성한 인증 URL을 View로 전달 */
        return "naverLogin.jsp";
    }


    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/naver")
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws Exception {
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

        // 등록된 회원이 아니면 닉네임 입력 폼으로 이동
        UserVO user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute(("email"), email);
            return "nicknameForm.jsp";
        }
        session.setAttribute("user", user);

        /* 네이버 로그인 성공 페이지 View 호출 */
        return "index.jsp";
    }
}