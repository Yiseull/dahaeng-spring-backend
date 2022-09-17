package com.dahaeng.controller;

import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    // 로그인은 정보 조회지만 Get을 사용하는 경우 ID, PW 정보가 노출되기 떄문에 Post 사용
    @PostMapping("/login")
    public ResponseEntity<UserVO> login(@RequestBody Map<String, Object> param) {
        ResponseEntity<UserVO> entity = null;
        UserVO user = null;
        String email = (String) param.get("email");
        String password = (String) param.get("password");

        user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                System.out.println("비밀번호가 일치합니다.");
                entity = new ResponseEntity<UserVO>(user, HttpStatus.OK);
            } else {
                System.out.println("비밀번호가 불일치합니다.");
                entity = new ResponseEntity<UserVO>(HttpStatus.BAD_REQUEST);
            }
        } else {
            entity = new ResponseEntity<UserVO>(HttpStatus.NOT_FOUND);
        }

        return entity;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        UserVO vo = new UserVO();
        vo.setEmail((String) param.get("email"));
        vo.setPassword((String) param.get("password"));
        vo.setNickname((String) param.get("nickname"));

        try {
            userService.insertUser(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        String email = (String) param.get("email");

        // 해당 이메일을 가진 유저가 없으면 FAIL 반환
        UserVO user = userService.findByEmail(email);
        if (user == null) {
            entity = new ResponseEntity<String>("FAIL", HttpStatus.NOT_FOUND);
            return entity;
        }

        try {
            userService.deleteUser(email);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @Value("${mail.username}")
    private String mailusername;
    @PostMapping("/mail-authentication")
    public ResponseEntity<JSONObject> mailAuthentication(@RequestBody Map<String, Object> param) throws Exception{
        ResponseEntity<JSONObject> entity = null;
        String email = (String) param.get("email");

        /* 인증번호(난수) 생성 */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        System.out.println("인증번호 " + checkNum);

        String setFrom = mailusername;
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + checkNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);

            JSONObject obj = new JSONObject();
            obj.put("checkNum", Integer.toString(checkNum));

            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    /*메일 중복 확인*/
    @PostMapping(value = "/check-mail")
    public ResponseEntity<String> checkMail(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        String email = (String) param.get("email");

        UserVO user = userService.findByEmail(email);
        if(user != null) {
            entity = new ResponseEntity<String>("EXIST", HttpStatus.OK);
        } else {
            entity = new ResponseEntity<String>("NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    /*닉네임 중복 확인*/
    @PostMapping("/check-nickname")
    public ResponseEntity<String> checkNickname(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        String nickname = (String) param.get("nickname");

        UserVO user = userService.findByNickname(nickname);
        if(user != null) {
            entity = new ResponseEntity<String>("EXIST", HttpStatus.OK);
        } else {
            entity = new ResponseEntity<String>("NOT EXIST", HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping(value = "/find-member")
    public ResponseEntity<List<UserVO>> findMember(@RequestBody Map<String, Object> param) {
        ResponseEntity<List<UserVO>> entity = null;
        String email = (String) param.get("email");

        try {
            entity = new ResponseEntity<List<UserVO>>(
                    userService.findMember(email), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/edit-nickname")
    public ResponseEntity<String> editNickname(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        String email = (String) param.get("email");
        String nickname = (String) param.get("nickname");

        // 해당 이메일을 가진 유저가 없으면 FAIL 반환
        UserVO user = userService.findByEmail(email);
        if (user == null) {
            entity = new ResponseEntity<String>("FAIL", HttpStatus.NOT_FOUND);
            return entity;
        }

        try {
            userService.editNickname(email, nickname);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/edit-password")
    public ResponseEntity<String> editPassword(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        String email = (String) param.get("email");
        String password = (String) param.get("password");

        // 해당 이메일을 가진 유저가 없으면 FAIL 반환
        UserVO user = userService.findByEmail(email);
        if (user == null) {
            entity = new ResponseEntity<String>("FAIL", HttpStatus.NOT_FOUND);
            return entity;
        }

        try {
            userService.editPassword(email, password);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}

