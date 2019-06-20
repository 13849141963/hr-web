package com.fescotech.apps.entryonline.support;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fesco.pafa.util.StringUtil;
import com.fescotech.apps.entryonline.dto.WebUserDto;
import com.fescotech.apps.entryonline.entity.WebUserVo;
import com.fescotech.apps.entryonline.util.HRPlateformApiUtil;
import com.fescotech.apps.entryonline.util.ResException;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;
public class SysUserSupport {
	
	static Logger logger = Logger.getLogger(SysUserSupport.class);

	
	/**
	 * 用户登录验证
	 * @param loginName
	 * @param userPwd
	 * @param checkLoginUrl
	 * @return
	 */
	public static WebUserVo checkSysUser(String loginName,String userPwd){
        
        WebUserDto wud =new WebUserDto();
        wud.setHrAccount(loginName);
        wud.setPassword(userPwd);
        //wud.setUserType(1);
        WebUserVo user = null;
		try {
			//String callRslt = HRPlateformApiUtil.callNewInterfaceByInnerNetWork("hrmgr.hrLogin", JSON.toJSONString(wud));
			String method = "hrmgr.hrLogin";
			String callRslt = ApiUtilOpenPlateform.callOpenPlateformApiHr(method, JSON.toJSONString(wud));
			logger.info("userLogin ---> res[" + callRslt + "]");
			Result res = JSONObject.parseObject(callRslt, Result.class);
			user = JSON.parseObject(JSON.toJSONString(res.getSuccessResult()), WebUserVo.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return user;
	}
}