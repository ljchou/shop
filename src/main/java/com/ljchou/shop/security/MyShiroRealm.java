package com.ljchou.shop.security;

import com.ljchou.shop.entity.User;
import com.ljchou.shop.entity.UserRoles;
import com.ljchou.shop.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("###执行权限校验###");
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        User user = userMapper.findByName(loginName);
        if (null == user) {
            return null;
        }

        // 权限对象info, 用来存放查询用户的所有角色（role）及权限（permission）
        List<UserRoles> userRoles = userMapper.findUserRolesByName(loginName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleNames = new HashSet<>();
        for (UserRoles role:userRoles) {
            roleNames.add(role.getRoleName());
            // 用户角色对应的所有权限
            info.addStringPermission(role.getPermissonName());
        }

        // 用户的角色集合
        info.setRoles(roleNames);
        return info;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userMapper.findByName(token.getUsername());
        if (null != user) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
        return null;
    }
}
