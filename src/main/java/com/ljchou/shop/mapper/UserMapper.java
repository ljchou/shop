package com.ljchou.shop.mapper;


import com.ljchou.shop.entity.User;
import com.ljchou.shop.entity.UserRoles;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM t_user")
    @Results({@Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "userName", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class)})
    List<User> getAll();

    @Select("SELECT * FROM t_user WHERE username = #{name}")
    @Results({@Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "userName", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class)
    })
    User findByName(String name);

    @Select("SELECT u.username userName, u.password password, r.rolename roleName, per.permissonname permissonName " +
            "FROM t_user u INNER JOIN t_user_role ur ON u.id = ur.user_id " +
            "INNER JOIN t_role r ON ur.role_id = r.id " +
            "INNER JOIN t_permission per ON r.id = per.role_id " +
            "WHERE u.username = #{name}")
    @Results({@Result(property = "userName", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "roleName", column = "rolename", javaType = String.class),
            @Result(property = "permissonName", column = "permissonname", javaType = String.class)
    })
    List<UserRoles> findUserRolesByName(String name);
}
