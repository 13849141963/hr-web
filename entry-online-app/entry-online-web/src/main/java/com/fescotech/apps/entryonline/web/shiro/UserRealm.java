package com.fescotech.apps.entryonline.web.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.fescotech.apps.entryonline.entity.WebUserVo;
import com.fescotech.apps.entryonline.support.SysUserSupport;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.config.AppConfig;
import com.fescotech.common.util.StringUtils;

/**
 * 认证
 */
public class UserRealm extends AuthorizingRealm {
     
    
    


	/**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		WebUserVo user = (WebUserVo)principals.getPrimaryPrincipal();
		List<String> permsList = null;//user.getPerms();

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isNullOrEmpty(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo info = null;
		String  username= (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        
        ShiroUtils.setSessionAttribute("username", username);
        
		if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
			throw new UnknownAccountException("用户名或密码为空");
		}
		
        String checkLoginUrl = AppConfig.me().getPropValue("service.remote.user.url") + AppConfig.me().getPropValue("service.remote.user.authUrl");
        WebUserVo userVo = SysUserSupport.checkSysUser(username, password);
		if(userVo == null){
			throw new IncorrectCredentialsException("用户名或密码错误");
		}
		Integer dutyType = userVo.getDutyType();
        Integer userType = userVo.getUserType();
        
		if(dutyType != null && dutyType == 2 && userType != null && userType == 7){
        	throw new IncorrectCredentialsException("暂无权限访问");
        }
		ShiroUtils.setSessionAttribute("userVo", userVo);
		ShiroUtils.setSessionAttribute("userType", userVo.getUserType());
		//将登陆用户的权限存进session
		List<String> permsList =new ArrayList<String>();
		ShiroUtils.setSessionAttribute(userVo.getLoginName()+"-perms", permsList);
		
		info = new SimpleAuthenticationInfo(userVo, password, getName());
        return info;
	}

}
