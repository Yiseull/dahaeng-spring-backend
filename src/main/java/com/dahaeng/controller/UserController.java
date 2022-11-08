package com.dahaeng.controller;

import com.dahaeng.biz.note.NoteVO;
import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    // 로그인은 정보 조회지만 Get을 사용하는 경우 ID, PW 정보가 노출되기 떄문에 Post 사용
    @PostMapping("/login")
    public ResponseEntity<UserVO> login(@RequestBody Map<String, Object> param) {
        ResponseEntity<UserVO> entity = null;
        UserVO user = null;
        String email = (String) param.get("email");
        String password = (String) param.get("password");

        user = userService.findByEmail(email);

        if (user != null) {
            if (pwdEncoder.matches(password ,user.getPassword())) {
                System.out.println("비밀번호가 일치합니다.");
                entity = new ResponseEntity<>(user, HttpStatus.OK);
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
        String encodePwd = pwdEncoder.encode((String) param.get("password"));
        System.out.println("암호화: " + encodePwd);
        UserVO vo = new UserVO();
        vo.setEmail((String) param.get("email"));
        vo.setPassword(encodePwd);
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

    @PostMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();
        String email = (String) param.get("email");

        // 해당 이메일을 가진 유저가 없으면 FAIL 반환
        UserVO user = userService.findByEmail(email);
        if (user == null) {
            obj.put("result", "FAIL");
            entity = new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
            return entity;
        }

        try {
            userService.deleteUser(email);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }



    @Value("${mail.username}")
    private String mailusername;
    @PostMapping("/mail-authentication")
    @ResponseBody
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
    public ResponseEntity<JSONObject> checkMail(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        String email = (String) param.get("email");

        JSONObject obj = new JSONObject();

        UserVO user = userService.findByEmail(email);
        if(user != null) {
            obj.put("result", "EXIST");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        } else {
            obj.put("result", "NOT EXIST");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        }
        return entity;
    }

    /*닉네임 중복 확인*/
    @PostMapping("/check-nickname")
    public ResponseEntity<JSONObject> checkNickname(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();
        String nickname = (String) param.get("nickname");

        UserVO user = userService.findByNickname(nickname);
        if(user != null) {
            obj.put("result", "EXIST");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.BAD_REQUEST);
        } else {
            obj.put("result", "NOT EXIST");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
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
    public ResponseEntity<JSONObject> editNickname(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();
        String email = (String) param.get("email");
        String nickname = (String) param.get("nickname");

        // 해당 이메일을 가진 유저가 없으면 FAIL 반환
        UserVO user = userService.findByEmail(email);
        if (user == null) {
            obj.put("result", "FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.NOT_FOUND);
            return entity;
        }

        try {
            userService.editNickname(email, nickname);
            user = userService.findByEmail(email);
            obj.put("nickname", user.getNickname());
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @PostMapping("/edit-password")
    public ResponseEntity<JSONObject> editPassword(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();
        String email = (String) param.get("email");
        String changeencodepwd = pwdEncoder.encode((String) param.get("password"));


        // 해당 이메일을 가진 유저가 없으면 FAIL 반환
        UserVO user = userService.findByEmail(email);

        if (user == null) {
            obj.put("result", "FAIL");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.NOT_FOUND);
            return entity;
        }

        try {
            userService.editPassword(email, changeencodepwd);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    /*비밀번호 찾기*/
    @PostMapping("/make-randompassword")
    @ResponseBody
    public ResponseEntity<JSONObject> makeRandompassword(@RequestBody Map<String, Object> param) throws Exception{
        ResponseEntity<JSONObject> entity = null;
        String email = (String) param.get("email");

        //UserVO user = userService.findByEmail(email);

        /*임시비밀번호 만들기*/
        int index = 0;
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };   //배열안의 문자 숫자는 원하는대로

        StringBuffer password = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 10 ; i++) {
            double rd = random.nextDouble();
            index = (int) (charSet.length * rd);

            password.append(charSet[index]);
        }

        String randompwd = password.toString();

        String setFrom = mailusername;
        String toMail = email;
        String title = "임시 비밀번호 전송 메일입니다.";
        String content =
                "임시 비밀번호를 전송해드립니다." +
                        "<br><br>" +
                        "임시 비밀번호는 " + randompwd + "입니다." +
                        "<br>" +
                        "해당 임시 비밀번호를 사용하여 로그인하시기 바랍니다.";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);

            String changeencodepwd = pwdEncoder.encode(randompwd);
            userService.editPassword(email, changeencodepwd);

            JSONObject obj = new JSONObject();
            obj.put("randompwd", randompwd);

            entity = new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/memberlist")
    public ResponseEntity<List<JSONObject>> memberlist(@RequestBody Map<String, Object> param) {
        ResponseEntity<List<JSONObject>> entity = null;
        int noteId = (int) param.get("noteId");
        List<UserVO> getmemberlist = userService.getMemberList(noteId);
        List<JSONObject> memberlist = new ArrayList<>();

        for(int i=0;i<getmemberlist.size();i++){
            UserVO vo = getmemberlist.get(i);
            JSONObject obj = new JSONObject();
            obj.put("email", vo.getEmail());
            obj.put("nickname", vo.getNickname());
            memberlist.add(obj);
        }
        try {
            entity = new ResponseEntity<>(memberlist, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}