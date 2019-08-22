package com.xiao.amovie.repository;


import com.xiao.amovie.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xiao
 */
@Repository
public interface UserRepository {

    @Insert("insert into user(nickname,email,password,salt,phone,gender,role) values(#{nickname},#{email},#{password},#{salt},#{phone},#{gender},#{role})")
    int insert(User user);

    @Select("select * from user where email = #{email}")
    User findByEmail(String email);

    @Delete("delete from user where id=#{id}")
    int delete(Integer id);

    @Update("update user set nickname=#{nickname},email=#{email},password=#{password},phone=#{phone},gender=#{gender},role=#{role} where id=#{id}")
    int update(User user);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);
}
