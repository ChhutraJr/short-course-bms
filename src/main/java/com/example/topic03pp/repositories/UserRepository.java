package com.example.topic03pp.repositories;

import com.example.topic03pp.models.Role;
import com.example.topic03pp.models.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    @Select("select id, username, password, status, profile_img from tb_user where username=#{username}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "roles", many = @Many(select = "getRolesByUserId")),
            @Result(column = "profile_img", property = "profileImg")
    })
    User loadUserByUsernameRepository(String username);


    @Select("select id, role from tb_role r inner join tb_user_role ur on r.id = ur.role_id where ur.user_id = #{id}")
    List<Role> getRolesByUserId(Integer id);


}
