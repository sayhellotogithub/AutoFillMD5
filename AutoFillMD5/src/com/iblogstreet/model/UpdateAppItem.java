package com.iblogstreet.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 用于版本更新
 */
@XStreamAlias("updateAppItem")
public class UpdateAppItem {
    // 设置为XML 的 attribute
    @XStreamAsAttribute()
    String packageName;
    String version;
    String versionString;
    String name;
    boolean md5Check;
    String md5;
    String url;
    String command;
    String fullName;

    public String getFullName() {
        return fullName;
    }

    public UpdateAppItem(String packageName, String version, String versionString, String name, boolean md5Check, String md5, String url, String command, String fullName) {
        this.packageName = packageName;
        this.version = version;
        this.versionString = versionString;
        this.name = name;
        this.md5Check = md5Check;
        this.md5 = md5;
        this.url = url;
        this.command = command;
        this.fullName = fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    //将字段名在XML中去掉
    //  @XStreamImplicit()


    public UpdateAppItem() {
    }

    public UpdateAppItem(String packageName, String version, String versionString, String name, boolean md5Check, String md5, String url, String command) {
        this.packageName = packageName;
        this.version = version;
        this.versionString = versionString;
        this.name = name;
        this.md5Check = md5Check;
        this.md5 = md5;
        this.url = url;
        this.command = command;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionString() {
        return versionString;
    }

    public void setVersionString(String versionString) {
        this.versionString = versionString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMd5Check() {
        return md5Check;
    }

    public void setMd5Check(boolean md5Check) {
        this.md5Check = md5Check;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
