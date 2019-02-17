package com.imooc.service;

import com.imooc.dao.UserDao;
import com.imooc.domain.MiaoshaUser;
import com.imooc.redis.RedisService;
import com.imooc.redis.SeckillUserKey;
import com.imooc.redis.UserKey;
import com.imooc.result.ResultVoUtils;
import com.imooc.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.rmi.runtime.Log;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    public static final String COOKIE_NAME = "token";

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(long id) {
        // 取缓存
        MiaoshaUser user = redisService.get(UserKey.getById, ""+id, MiaoshaUser.class);
        if (user != null) {
            return user;
        }
        // 取数据库
        user = userDao.getById(id);
        if (user != null) {
            redisService.set(UserKey.getById, ""+id, user);
        }

        return userDao.getById(id);
    }

    public boolean updatePassword(String token, long id, String formPass) {
        // 取user
        MiaoshaUser user = getById(id);
        if (user == null) {
            System.out.println("user为空");
            return false;
        }
        // 更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.md5Encryption(formPass));
        userDao.update(toBeUpdate);
        // 处理缓存
        redisService.delete(UserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(UserKey.token, token, user);
        return true;
    }

    public MiaoshaUser getByNickname(String nickname) {
        return userDao.getByNickname(nickname);
    }

    public MiaoshaUser getByNamePassword(String nickname, String password) {
        return userDao.getByNamePassword(nickname, password);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(SeckillUserKey.token, token, MiaoshaUser.class);
        // 延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(SeckillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
