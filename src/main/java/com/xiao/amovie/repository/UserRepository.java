package com.xiao.amovie.repository;


import com.xiao.amovie.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    @Insert("insert into user(nickname,email,password,salt,phone,gender,role) values(#{nickname},#{email},#{password},#{salt},#{phone},#{gender},#{role})")
    int insert(User user);

    @Select("select * from user where email = #{email}")
    User findByEmail(String email);
}
