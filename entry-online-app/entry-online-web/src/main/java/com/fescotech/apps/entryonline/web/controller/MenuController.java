package com.fescotech.apps.entryonline.web.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.entity.EntryMenu;
import com.fescotech.apps.entryonline.entity.Menu;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.RoleMenu;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@RestController
@RequestMapping("/base/menu")
public class MenuController {
	
	

	@RequestMapping("/user")
	public R user() throws Exception {

         /**
          * 从接口获取菜单
          */
		List<Menu> menuList = buildMenuFromNet();
		
		/**
		 * 从本地配置获取菜单
		 */
		//List<Menu> menuList = buildMenuByXml();

		//返回menuList
		return R.ok().put("menuList", menuList);
	}

	/**
	 * 从接口获取菜单
	 * @return
	 * @throws Exception
	   @author guoliming
	 */
	private List<Menu> buildMenuFromNet() throws Exception {
		//从接口获取菜单
		Map<String, Object> map = new HashMap<>();
		String username = (String) ShiroUtils.getSessionAttribute("username");
		map.put("userAccount", username);
		Integer userType =  (Integer) ShiroUtils.getSessionAttribute("userType");
		map.put("userType", userType);
		String jsonStr = JSON.toJSONString(map);
		//String roleMenuResultStr = HttpUtils.jsonPost("http://10.0.75.151:8847/hrmgr/getUserRoleMenu", jsonStr);
		String method = "hrmgr.getUserRoleMenu";
		String roleMenuResultStr = ApiUtilOpenPlateform.callOpenPlateformApiHr(method,jsonStr);
		Result result = JSON.parseObject(roleMenuResultStr, Result.class);
		Object successResult = result.getSuccessResult();
		String successResultStr = JSON.toJSONString(successResult);
		List<RoleMenu> roleMenus = JSON.parseArray(successResultStr, RoleMenu.class);

		System.out.println("--------------"+successResult);
		List<EntryMenu> entryMenuList =  new ArrayList<EntryMenu>();

		for (RoleMenu roleMenu: roleMenus) {
			entryMenuList.removeAll(roleMenu.getNsEsMenus());
			entryMenuList.addAll(roleMenu.getNsEsMenus());
		}
		//排除某些菜单
		for(int i = 0 ;i<entryMenuList.size();i++){
			Integer id = entryMenuList.get(i).getId();
			if (id == null || id.equals(121)){
				entryMenuList.remove(i);
				i=i-1;
			}
		}

		//组装菜单为前端可用
		//新建list<Menu> 为第一级菜单menu
		List<Menu> menuList = new ArrayList<Menu>();
		//因为该程序只有一个一级menu,故直接new,内容人为设定
		Menu onlyParentMenu = new Menu();
		onlyParentMenu.setIcon("fa fa-user-secret");
		onlyParentMenu.setMenuId(0L);
		onlyParentMenu.setName("Onsite管理");
		onlyParentMenu.setType(0);
		onlyParentMenu.setUrl(null);

		//组装二级菜单
		List<Menu> secondMenuList = new ArrayList<Menu>();
		for (EntryMenu entryMenu:entryMenuList) {
			Menu menu = new Menu();
			//todo;看icon是否写死
			menu.setIcon("fa fa-plus-square");
			menu.setMenuId(entryMenu.getId().longValue());
			menu.setName(entryMenu.getMenuName());
			menu.setType(1);
			menu.setUrl(entryMenu.getMenuUrl());
			secondMenuList.add(menu);
		}

		//将二级菜单集放入一级菜单
		onlyParentMenu.setList(secondMenuList);
		//将一级菜单放入menuList
		menuList.add(onlyParentMenu);
		return menuList;
	}

	/**
	 * 从xml获取菜单
	 * @return
	   @author guoliming
	 */
	private List<Menu> buildMenuByXml() {
		List<Menu> menuList = new ArrayList<Menu>();
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        InputStream is = this.getClass().getResourceAsStream("/menu.xml");
        try {   
            DocumentBuilder builder = factory.newDocumentBuilder();   
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("menu");
	        // 遍历每一个节点
            //TODO 根据当前用户权限取得菜单
            for (int i = 0; i < list.getLength(); ++i)
	        {
	        	Element element = (Element) list.item(i);	        	
	            if(element.getAttribute("type").equals("0")){
	            	if (true) {//导航菜单权限控制
	            		Menu menu = new Menu();
			        	menu.setIcon(element.getAttribute("icon"));
			        	menu.setMenuId(Long.parseLong(element.getAttribute("menuId")));
			        	menu.setName(element.getAttribute("name"));
			        	menu.setType(Integer.parseInt(element.getAttribute("type")));
			        	menu.setUrl(element.getAttribute("url"));
		            	List<Menu> menus = new ArrayList<Menu>();
		            	NodeList mlist = element.getElementsByTagName("menu");
			            for(int j = 0; j < mlist.getLength(); ++j){//菜单权限控制
			            	Element ele = (Element) mlist.item(j);
			            	if(true){
			            		Menu me = new Menu();
				            	me.setIcon(ele.getAttribute("icon"));
				            	me.setMenuId(Long.parseLong(ele.getAttribute("menuId")));
				            	me.setName(ele.getAttribute("name"));
				            	me.setType(Integer.parseInt(ele.getAttribute("type")));
				            	me.setUrl(ele.getAttribute("url"));
				            	menus.add(me);
			            	}		            		            	
			            }
			            menu.setList(menus);
			            menuList.add(menu);
					}
	            }	            
	        }
        }catch (Exception e) {
        	 e.printStackTrace();
		}
		return menuList;
	}
}