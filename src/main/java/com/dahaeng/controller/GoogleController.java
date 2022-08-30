package com.dahaeng.controller;

import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Controller
public class GoogleController {

    @Autowired
    private UserService userService;

    @Value("${google.client.id}")
    private String CLIENT_ID;

    @PostMapping("/google")
    public String googleAuth(@RequestParam("credential") String idtoken, Model model, HttpSession session) throws GeneralSecurityException, IOException {
        System.out.println("GoogleController2");

        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idtoken);
        } catch(GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();

            // 등록된 회원이 아니면 닉네임 입력 폼으로 이동
            UserVO user = userService.findByEmail(email);
            if (user == null) {
                model.addAttribute(("email"), email);
                return "/nicknameForm.jsp";
            }
            session.setAttribute("user", user);

        } else {
            System.out.println("Invalid ID token.");
            return "/login";
        }
        // 등록된 회원이면
        return "index.jsp";
    }

    @PostMapping("/googleSignUp")
    public String googleSignUp(UserVO vo, HttpSession session) {
        vo.setPassword("");
        userService.insertUser(vo);
        UserVO user = userService.findByEmail(vo.getEmail());
        session.setAttribute("user", user);
        return "index.jsp";
    }

}