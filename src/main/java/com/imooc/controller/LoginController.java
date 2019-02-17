package com.imooc.controller;

import com.imooc.domain.MiaoshaUser;
import com.imooc.result.ResultVo;
import com.imooc.result.ResultVoUtils;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Util;
import com.imooc.utils.UUIDUtil;
import com.imooc.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResultVo login(HttpServletResponse response, @Valid LoginVo loginVo, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                return ResultVoUtils.error(error.getDefaultMessage());

            }
        }
        MiaoshaUser user1 = userService.getByNickname(loginVo.getMobile());
        String password = MD5Util.md5Encryption(loginVo.getPassword());
        MiaoshaUser user2 = userService.getByNamePassword(loginVo.getMobile(), password);
        if (null == user1) {
            return ResultVoUtils.error("用户名不存在");
        }
        if (null == user2) {
            return ResultVoUtils.error("密码错误");
        }
        String token = UUIDUtil.uuid();
        userService.addCookie(response, token, user2);
        return ResultVoUtils.success(null);
    }

}
