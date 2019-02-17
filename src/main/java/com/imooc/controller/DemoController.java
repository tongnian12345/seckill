package com.imooc.controller;

import com.imooc.domain.MiaoshaUser;
import com.imooc.redis.RedisService;
import com.imooc.redis.UserKey;
import com.imooc.result.ResultVo;
import com.imooc.result.ResultVoUtils;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/redis/get")
    @ResponseBody
    public ResultVo redisGet() {
        MiaoshaUser user = redisService.get(UserKey.getById, ""+1, MiaoshaUser.class);
        return ResultVoUtils.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public ResultVo redisSet() {
        MiaoshaUser user = new MiaoshaUser();
        user.setId(1);
        user.setNickname("1111");
        redisService.set(UserKey.getById, "" + 1, user);
        return ResultVoUtils.success(true);
    }


    @RequestMapping("/user")
    @ResponseBody
    public ResultVo findById() {
        MiaoshaUser user = userService.getById(1);
        return ResultVoUtils.success(user);
    }


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "hello world";
    }


    @RequestMapping("/hello")
    @ResponseBody
    public ResultVo hello() {
        return ResultVoUtils.success("success");
    }


    @RequestMapping("/hasError")
    @ResponseBody
    public ResultVo error() {
        return ResultVoUtils.error("失败了");
    }


    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Tom");
        return "hello";
    }

}
