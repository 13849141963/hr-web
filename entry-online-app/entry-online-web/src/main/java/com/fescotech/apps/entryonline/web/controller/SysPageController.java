package com.fescotech.apps.entryonline.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图 由于需要针对每个页面进行精确权限控制，考虑每个页面配置一个方法（与权限对应） 或者拦截器验证权限，待进一步优化
 */
@Controller
public class SysPageController {
	@RequestMapping("{folder}/{url}.html")
	public String page(@PathVariable("folder") String folder, @PathVariable("url") String url) {
		return folder + "/" + url + ".html";
	}

	@RequestMapping("{folder}/{url}.xhtml")
	public String pageJsp(@PathVariable("folder") String folder, @PathVariable("url") String url) {
		return folder + "/" + url + ".jsp";
	}
}