package com.hss01248.spmvc.auth;

import com.hss01248.spmvc.auth.mapper.SysUserMapper;
import com.hss01248.spmvc.auth.po.SysUser;
import com.hss01248.spmvc.auth.po.SysUserExample;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    SysUserMapper userMapper;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userCode = (String) authenticationToken.getPrincipal();
        SysUserExample example = new SysUserExample();

        //从数据库查
        List<SysUser> userList = userMapper.selectByExample(example);
        if(userList ==null || userList.size()==0){
            return null;
        }
        SysUser user = userList.get(0);
        String pw = user.getPassword();
        String salt = user.getSalt();
        return new SimpleAuthenticationInfo(userCode,pw, ByteSource.Util.bytes(salt),this.getName());
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userCode = (String) principalCollection.getPrimaryPrincipal();

        //从数据库拿到权限信息
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create");
        permissions.add("items:add");

        //添加到授权信息对象中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);

        return info;
    }


    @Override
    public String getName() {
        return "customRealm";
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
