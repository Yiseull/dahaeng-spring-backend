package com.dahaeng.controller;

import com.dahaeng.biz.user.UserService;
import com.dahaeng.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/login")
    public String login(UserVO vo, Model model, HttpServletRequest request) {
        UserVO user = userService.findByEmailAndPassword(vo);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        if (user != null) {
            model.addAttribute(("user"), user);
            return "index.jsp";
        }
        return "login.jsp";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session, @ModelAttribute("user") UserVO vo) {
        session.invalidate();
        vo.setEmail("");
        vo.setPassword("");
        vo.setNickname("");
        return "login.jsp";
    }

    @PostMapping("/join")
    public String join(UserVO vo) {
        userService.insertUser(vo);
        return "login.jsp";
    }

    @RequestMapping("/withdrawal")
    public String withdrawal(@ModelAttribute("user") UserVO vo) {
        userService.deleteUser(vo);
        vo.setEmail("");
        vo.setPassword("");
        vo.setNickname("");
        return "login.jsp";
    }

    @Value("${mail.username}")
    private String mailusername;
    @RequestMapping(value="/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception{

        /* 뷰(View)로부터 넘어온 데이터 확인 */
        System.out.println("이메일 데이터 전송 확인");
        System.out.println("인증번호 : " + email);

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

        }catch(Exception e) {
            e.printStackTrace();
        }

        String num = Integer.toString(checkNum);

        return num;
    }

    /*메일 중복 확인*/
    @RequestMapping(value = "/memberMailChk", method = RequestMethod.POST)
    @ResponseBody
    public String memberMailChkPOST(UserVO vo, String memberMail) throws Exception {

        System.out.println("memberMailChk() 진입 => 입력 중인 메일:" + memberMail);

        UserVO result = userService.findByEmail(memberMail);

        System.out.println("result 다음");


        if(result != null) {
            System.out.println("중복메일 존재");
            return "fail";

        } else {
            System.out.println("중복메일 없음");
            return "success";

        }
    }

    /*닉네임 중복 확인*/
    @RequestMapping(value = "/nickNameChk", method = RequestMethod.POST)
    @ResponseBody
    public String nickNameChkPOST(UserVO vo, String nickName) throws Exception {

        System.out.println("nickNameChk() 진입 => 입력 중인 닉네임:" + nickName);

        UserVO result = userService.findByNickname(nickName);

        System.out.println("result 다음");


        if(result != null) {
            System.out.println("중복닉네임 존재");
            return "fail";

        } else {
            System.out.println("중복닉네임 없음");
            return "success";

        }
    }

    @RequestMapping(value = "/findMember", method = RequestMethod.GET)
    public String findMember(Model model, String email) {

        List <UserVO> findmember = userService.findMember(email);
        model.addAttribute("findMemberList", findmember);

        return "inviteMember.jsp";
    }

    @RequestMapping("/editNickname")
    public String editNickname(@ModelAttribute("user") UserVO vo, HttpSession session) {
        System.out.println(vo);
        UserVO user = userService.updateUser(vo, "nickname");
        vo.setNickname(user.getNickname());
        return "index.jsp";
    }

    @RequestMapping("/editPassword")
    public String editPassword(@ModelAttribute("user") UserVO vo, HttpSession session) {
        System.out.println(vo);
        UserVO user = userService.updateUser(vo, "password");
        vo.setNickname(user.getPassword());
        return "index.jsp";
    }
}

