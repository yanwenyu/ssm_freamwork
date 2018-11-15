package com.duyi.hrb.controller;

import com.duyi.hrb.domain.User;
import com.duyi.hrb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/findByName")
    public void findByName(@RequestParam(name = "name") String name, HttpServletResponse res) throws IOException {
        User user = new User();
        user.setUsername(name);
        user = userService.findByName(user);
        res.getWriter().write(user.toString());
    }
}
