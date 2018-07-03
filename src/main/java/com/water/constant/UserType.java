package com.water.constant;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 10:05 2018/7/3
 * @Modified By:
 */
public enum UserType {
    SUPER_MANAGER("超级管理员",0),
    FACTORY_MANAGER("普通水厂管理员",1),
    EMPLOYEE("普通雇员",2);
    private String TypeName;
    private Integer userType;

    UserType(String typeName, Integer userType) {
        TypeName = typeName;
        this.userType = userType;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
