package com.iblogstreet.model;/**
 * Created by Administrator on 2018/2/6.
 */

import com.iblogstreet.model.UpdateApps;

/**
 * 项目名称：CJPAD-C-V4.0
 * 类描述：配制文件bean
 * 创建人：Administrator王军
 * 创建时间：2018/2/6
 */
public class ConfigXmlBean {
    String fileName;
    UpdateApps updateApps;

    public ConfigXmlBean(String fileName, UpdateApps updateApps) {
        this.fileName = fileName;
        this.updateApps = updateApps;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UpdateApps getUpdateApps() {
        return updateApps;
    }

    public void setUpdateApps(UpdateApps updateApps) {
        this.updateApps = updateApps;
    }
}
