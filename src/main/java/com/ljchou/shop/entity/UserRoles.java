package com.ljchou.shop.entity;

public class UserRoles {
    private String userName;

    private String password;

    private String roleName;

    private String permissonName;

    public String getPermissonName() {
        return permissonName;
    }

    public void setPermissonName(String permissonName) {
        this.permissonName = permissonName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String toString() {
        return String.format("userRole info: userName: %s-password :%s-rolenName :%s-permissionname", userName, password, roleName, permissonName);
    }
}
