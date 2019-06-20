package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.List;

public class MenuObj {
	private Integer roleId;
	private String roleName;
	
	private List<MenuItme> nsEsMenus;
	
	
	
	
	public Integer getRoleId() {
		return roleId;
	}




	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}




	public String getRoleName() {
		return roleName;
	}




	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}




	public List<MenuItme> getNsEsMenus() {
		return nsEsMenus;
	}




	public void setNsEsMenus(List<MenuItme> nsEsMenus) {
		this.nsEsMenus = nsEsMenus;
	}




	public class MenuItme{
		private Integer id;
		private String menuCode;
		private String menuName;
		private String menuUrl;
		private Integer menuIndex;
		private String systemModel;
		private Integer menuType;
		private Integer pmenuId;
		
		
		
		
		public Integer getPmenuId() {
			return pmenuId;
		}

		public void setPmenuId(Integer pmenuId) {
			this.pmenuId = pmenuId;
		}

		public int getId() {
			return id;
		}
		
		public String getMenuCode() {
			return menuCode;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setMenuCode(String menuCode) {
			this.menuCode = menuCode;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getMenuUrl() {
			return menuUrl;
		}
		public void setMenuUrl(String menuUrl) {
			this.menuUrl = menuUrl;
		}
		public Integer getMenuIndex() {
			return menuIndex;
		}
		public void setMenuIndex(Integer menuIndex) {
			this.menuIndex = menuIndex;
		}
		public String getSystemModel() {
			return systemModel;
		}
		public void setSystemModel(String systemModel) {
			this.systemModel = systemModel;
		}
		public Integer getMenuType() {
			return menuType;
		}
		public void setMenuType(Integer menuType) {
			this.menuType = menuType;
		}
		
		
	}
	

}
