package com.dahaeng.controller;

import com.dahaeng.biz.UserService;
import com.dahaeng.biz.UserVO;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Controller
public class GoogleController {

    @Autowired
    private UserService userService;

    @Value("${google.client.id}")
    private String CLIENT_ID;

    @PostMapping("/index")
    public String googleAuth(@RequestParam("credential") String idtoken, Model model) throws GeneralSecurityException, IOException {
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
//            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//            String name = (String) payload.get("name");
//            String pictureUrl = (String) payload.get("picture");
//            String locale = (String) payload.get("locale");
//            String familyName = (String) payload.get("family_name");
//            String givenName = (String) payload.get("given_name");

            // 등록된 회원이 아니면 닉네임 입력 폼으로 이동
            UserVO user = userService.findByEmail(email);
            if (user == null) {
                model.addAttribute(("email"), email);
                return "nicknameForm.jsp";
            }
            model.addAttribute(("user"), user);

        } else {
            System.out.println("Invalid ID token.");
            return "redirect:login.jsp";
        }
        // 등록된 회원이면
        return "index.jsp";
    }

    @PostMapping("/googleSignUp")
    public String editUser(UserVO vo, Model model) {
        vo.setPassword("");
        userService.insertUser(vo);
        model.addAttribute(("user"), userService.findByEmail(vo.getEmail()));
        return "index.jsp";
    }

}
