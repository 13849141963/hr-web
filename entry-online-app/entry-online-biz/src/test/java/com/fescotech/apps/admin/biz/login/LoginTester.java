/*package com.fescotech.apps.admin.biz.login;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Assume;
import org.junit.Test;

import com.fesco.pafa.exceptions.MessageException;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.common.config.AppConfig;
import com.fescotech.common.user.domain.vo.WebSysUserVo;
import com.fescotech.common.user.support.SysUserSupport;

public class LoginTester {

	@Test
	public void testLogin1(){
		for(int i = 0 ; i <1000;i++){
			String callUrl = AppConfig.me().getPropValue("service.remote.authUrl");
			String user="testbybuser";
			String pwd=new Sha256Hash("123").toHex();
			WebSysUserVo suv = SysUserSupport.checkSysUser(user,pwd, callUrl);
			System.out.println(JsonBuilder.buildJsonParam(suv));
		}
	}
	
	@Test(expected = MessageException.class)  
	public void testLogin2()throws MessageException{
		String callUrl = AppConfig.me().getPropValue("service.remote.authUrl");
		String user="admin2";
		String pwd=new Sha256Hash("admin").toHex();
		WebSysUserVo suv = SysUserSupport.checkSysUser(user,pwd, callUrl);
		Assume.assumeTrue(suv!=null);
		System.out.println(JsonBuilder.buildJsonParam(suv));
	}
}	
*/