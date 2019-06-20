package com.fescotech.apps.entryonline.entity;

/**
 * Created by cy on 2018/3/7.
 */
public class EntryMenu {
    //菜单ID
    private Integer id;
    //菜单编码
    private String menuCode;
    //菜单名称
    private String menuName;
    //菜单URL
    private String menuUrl;
    //菜单顺序号
    private Integer MenuIndex;
    //所属功能模块 Employees / HR / Management
    private String systemModel;
    //菜单类型 10-主导航菜单 20-二级菜单 90-非导航菜单
    private Integer menuType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
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
        return MenuIndex;
    }

    public void setMenuIndex(Integer menuIndex) {
        MenuIndex = menuIndex;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EntryMenu entryMenu = (EntryMenu) o;

        if (id != null ? !id.equals(entryMenu.id) : entryMenu.id != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }
}
