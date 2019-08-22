package com.xiao.amovie.utils;

import com.xiao.amovie.VO.ReturnVO;

public class ReturnVOUtil {

    public static ReturnVO success() {
        ReturnVO returnVO = new ReturnVO();
        returnVO.setCode(200);
        returnVO.setMsg("成功");
        return returnVO;
    }

    public static  ReturnVO notFound() {
        ReturnVO returnVO = new ReturnVO();
        returnVO.setCode(404);
        returnVO.setMsg("资源未找到");
        return returnVO;
    }

    public static ReturnVO createFail(String msg) {
        ReturnVO returnVO = new ReturnVO();
        returnVO.setCode(404);
        returnVO.setMsg(msg);
        return returnVO;
    }

}
