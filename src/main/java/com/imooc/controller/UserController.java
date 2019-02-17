package com.imooc.controller;

import com.imooc.domain.MiaoshaUser;
import com.imooc.result.ResultVo;
import com.imooc.result.ResultVoUtils;
import com.imooc.service.GoodsService;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    @ResponseBody
    public ResultVo list(Model model, MiaoshaUser user) {
        return ResultVoUtils.success(user);
    }


}
