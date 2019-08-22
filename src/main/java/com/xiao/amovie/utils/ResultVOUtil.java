package com.xiao.amovie.utils;

import com.xiao.amovie.from.ResultForm;

/**
 * @author xiao
 */
public class ResultVOUtil {

    public static ResultForm success(){
        ResultForm resultForm = new ResultForm();
        resultForm.setMsg("成功");
        return resultForm;
    }


    public static ResultForm error(String msg){
        ResultForm resultForm = new ResultForm();
        resultForm.setMsg(msg);
        return resultForm;
    }
}
