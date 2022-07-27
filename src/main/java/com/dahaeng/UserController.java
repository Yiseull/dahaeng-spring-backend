package com.dahaeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(@ModelAttribute("user") UserVO vo) {
        System.out.println("로그인 화면으로 이동");
        return "login.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserVO vo, Model model) {
        System.out.println("로그인 인증 처리...");
        UserVO user = userService.getUser(vo);
        if (user != null) {
            model.addAttribute(("user"), userService.getUser(vo));
            return "index.jsp";
        }
        else return "login.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login.jsp";
    }

    @PostMapping("/join")
    public String join(UserVO vo) {
        userService.insertUser(vo);
        return "login.jsp";
    }

    @RequestMapping("/withdrawal")
    public String withdrawal(UserVO vo) {
        UserVO user = userService.getUser(vo);
        userService.deleteUser(user);
        return "join.jsp";
    }

}
