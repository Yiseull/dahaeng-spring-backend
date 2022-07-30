package com.dahaeng.controller;

import com.dahaeng.biz.UserService;
import com.dahaeng.biz.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "login.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserVO vo, Model model) {
        UserVO user = userService.findByEmailAndPassword(vo);
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
        return "join.jsp";
    }

}
