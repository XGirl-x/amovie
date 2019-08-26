package com.xiao.amovie.from;

import com.xiao.amovie.enums.Gender;
import com.xiao.amovie.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private String nickname;

    @Email(message = "请输入正确的邮箱格式")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String phone;

    /**
     * 性别
     * {@link com.xiao.amovie.enums.Gender}
     */
    private Integer gender = Gender.OTHER.getCode();

    /**
     * 角色
     * {@link com.xiao.amovie.enums.Role}
     */
    private String role = Role.USER.getMessage();
}
