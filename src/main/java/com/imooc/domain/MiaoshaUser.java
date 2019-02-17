package com.imooc.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MiaoshaUser {

    private long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private int loginCount;

}
