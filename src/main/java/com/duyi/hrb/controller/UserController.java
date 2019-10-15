package com.duyi.hrb.controller;

import com.duyi.hrb.domain.User;
import com.duyi.hrb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController extends BaseCotroller {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/findByName",method = RequestMethod.GET)
    public void findByName(@RequestParam(name = "username") String name, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        if (null == name || "".equals(name)){
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"username值为空无法查找",null);
            return;
        }
        User user = userService.findByName(name);
        if (null != user) {
            wirteResult(response,RespStatusEnum.SUCCESS.getStatus(),"查询成功",user);
        } else {
            wirteResult(response,RespStatusEnum.SUCCESS.getStatus(),"未找到结果",null);
        }
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public void addUser(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("tel") String tel,
                        HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        if ( null == username | "".equals(username)) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"用户名不能为空",null);
            return;
        }
        if ( null == password | "".equals(password)) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"密码不能为空",null);
            return;
        }
        if ( null == tel | "".equals(tel)) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"tel不能为空",null);
            return;
        }

        UserService.InsertStatus result = userService.addUser(username, password, tel);
        wirteResult(response,result.getRespStatus().getStatus(),result.getMsg(),null);
    }


    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public void delete(@RequestParam("id") int id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        if (id < 0) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"请输入有效Id",null);
            return;
        }

        if (userService.del(id)) {
            wirteResult(response,RespStatusEnum.SUCCESS.getStatus(),"删除成功",null);
        } else {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"删除失败",null);
        }

    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public void updateUser(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("tel") String tel,
                        @RequestParam("id") Integer id,
                        HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        if (id < 0) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"请输入有效Id",null);
            return;
        }
        if ( null == username | "".equals(username)) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"用户名不能为空",null);
            return;
        }
        if ( null == password | "".equals(password)) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"密码不能为空",null);
            return;
        }
        if ( null == tel | "".equals(tel)) {
            wirteResult(response,RespStatusEnum.FAIL.getStatus(),"tel不能为空",null);
            return;
        }
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setTel(tel);

        UserService.UpdateStatus result = userService.updateUser(user);
        wirteResult(response,result.getRespStatus().getStatus(),result.getMsg(),null);
    }
}
