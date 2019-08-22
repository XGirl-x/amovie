package com.xiao.amovie.utils;

import com.xiao.amovie.from.ReturnForm;

/**
 * @author xiao
 */
public class ReturnVOUtil {

    public static ReturnForm success() {
        ReturnForm returnForm = new ReturnForm();
        returnForm.setMsg("成功");
        return returnForm;
    }

    public static ReturnForm error(String msg) {
        ReturnForm returnForm = new ReturnForm();
        returnForm.setMsg(msg);
        return returnForm;
    }

}
