package com.galleon.springboot.mapper;

import com.galleon.springboot.entity.User;
import com.galleon.springboot.util.BaseMapper;
import org.apache.ibatis.annotations.*;

public interface UserMapper extends BaseMapper<User>{
    @Select("SELECT * FROM t_user " +
            "WHERE username=#{username} ")
    @Results(value = {
            @Result(property = "role", column = "role_id", one = @One(select ="com.galleon.springboot.mapper.RoleMapper.selectByPrimaryKey")),
    })
    User queryByUsername(@Param("username")String username);

}
