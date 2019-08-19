package com.xiao.amovie.utils;

import com.xiao.amovie.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("成功");
        return resultVO;
    }


    public static ResultVO error(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(msg);
        return resultVO;
    }
}
