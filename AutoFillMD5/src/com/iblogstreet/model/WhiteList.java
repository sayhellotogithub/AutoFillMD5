package com.iblogstreet.model;/**
 * Created by Administrator on 2018/2/6.
 */

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * 项目名称：CJPAD-C-V4.0
 * 类描述：白名单
 * 创建人：Administrator王军
 * 创建时间：2018/2/6
 */
@XStreamAlias("whiteList")
public class WhiteList {
    @XStreamAsAttribute()
    String version;
    @XStreamAsAttribute()
    String versionString;

    public WhiteList(String version, String versionString, boolean updateAllApp, List<String> whiteAppItems) {
        this.version = version;
        this.versionString = versionString;
        this.updateAllApp = updateAllApp;
        this.whiteAppItems = whiteAppItems;
    }

    @XStreamAsAttribute()

    boolean updateAllApp;

    List<String> whiteAppItems;

    public List<String> getWhiteAppItems() {
        return whiteAppItems;
    }

    public boolean isUpdateAllApp() {
        return updateAllApp;
    }

    public void setUpdateAllApp(boolean updateAllApp) {
        this.updateAllApp = updateAllApp;
    }

    public void setWhiteAppItems(List<String> whiteAppItems) {
        this.whiteAppItems = whiteAppItems;
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


}
