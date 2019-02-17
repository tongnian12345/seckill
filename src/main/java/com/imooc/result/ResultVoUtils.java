package com.imooc.result;

public class ResultVoUtils {

    public static ResultVo success(Object data) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.setCode(0);
        ResultVo.setMsg("成功");
        ResultVo.setData(data);
        return ResultVo;
    }

    public static ResultVo error(String msg) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.setCode(1);
        ResultVo.setMsg(msg);
        return ResultVo;
    }

}
