package com.fescotech.apps.entryonline.entity;

import java.util.List;

/**
 * Created by cy on 2018/3/7.
 */
public class RoleMenu {
    private Integer roleId;
    private String roleName;
    private List<EntryMenu> nsEsMenus;

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

    public List<EntryMenu> getNsEsMenus() {
        return nsEsMenus;
    }

    public void setNsEsMenus(List<EntryMenu> nsEsMenus) {
        this.nsEsMenus = nsEsMenus;
    }
}
