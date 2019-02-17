package com.imooc.vo;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class LoginVo {

    @NotNull(message = "账号不能为空")
    private String mobile;

    @NotNull
    private String password;

}
