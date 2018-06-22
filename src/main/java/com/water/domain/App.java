package com.water.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by yuanyuzhao on 2017/10/9.
 */
public class App {
    private Integer id;
    @NotBlank(message="应用标识不能为空")
    @Size(max = 30,message= "应用标识长度不能超过30")
    private String appId;
    @NotBlank(message="应用名称不能为空")
    @Size(max = 50,message= "应用名称长度不能超过50")
    private String name;
    @NotBlank(message="应用URL地址不能为空")
    private String url;
    private String resourceXmlFile;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer deleteStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourceXmlFile() {
        return resourceXmlFile;
    }

    public void setResourceXmlFile(String resourceXmlFile) {
        this.resourceXmlFile = resourceXmlFile;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", resourceXmlFile='" + resourceXmlFile + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", deleteStatus=" + deleteStatus +
                '}';
    }
}
