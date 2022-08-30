//package com.dahaeng.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.math.BigInteger;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.security.SecureRandom;
//
//@Controller
//public class NaverController {
//
//    // CSRF 방지를 위한 상태 토큰 생성 코드
//    // 상태 토큰은 추후 검증을 위해 세션에 저장되어야 한다.
//    public String generateState()
//    {
//        SecureRandom random = new SecureRandom();
//        return new BigInteger(130, random).toString(32);
//    }
//
//    public String generateState(HttpRequest request)
//    {
//        // 상태 토큰으로 사용할 랜덤 문자열 생성
//        String state = generateState();
//        // 세션 또는 별도의 저장 공간에 상태 토큰을 저장
//        request.session().attribute("state", state);
//        return state;
//    }
//
//
//    @RequestMapping("/naver")
//    public void naverOauth() {
//        String token = "YOUR_ACCESS_TOKEN";// 네아로 접근 토큰 값";
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//        try {
//            String apiURL = "https://openapi.naver.com/v1/nid/me";
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Authorization", header);
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if(responseCode==200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else {  // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//            }
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//            System.out.println(response.toString());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}