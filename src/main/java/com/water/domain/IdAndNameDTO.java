package com.water.domain;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 14:16 2018/7/3
 * @Modified By:
 */
public class IdAndNameDTO {
    private  Integer id;
    private String code;
    private  String name;
    private  String idAndName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdAndName() {
        return idAndName;
    }

    public void setIdAndName(String idAndName) {
        this.idAndName = idAndName;
    }

    @Override
    public String toString() {
        return "IdAndNameDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", idAndName='" + idAndName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
