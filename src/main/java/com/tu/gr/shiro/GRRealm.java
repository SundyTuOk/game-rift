package com.tu.gr.shiro;

import com.tu.gr.orm.dao.UserMapper;
import com.tu.gr.orm.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class GRRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (this.userMapper == null) {
            return null;
        }
        String userIDStr = (String) token.getPrincipal();
        Long userID = 0L;
        try {
            userID = Long.parseLong(userIDStr);
        } catch (NumberFormatException e) {
            logger.error("");
            return null;
        }

        User user = userMapper.selectByPrimaryKey(userID);
        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo
                = new SimpleAuthenticationInfo(user,user.getPassword(),"communityRealm");
        return simpleAuthenticationInfo;
    }

}
