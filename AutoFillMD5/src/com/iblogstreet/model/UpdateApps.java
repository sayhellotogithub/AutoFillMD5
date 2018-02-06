package com.iblogstreet.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * 项目名称：CJPAD-S-V1.2_TB3
 * 类描述：
 * 创建人：刘进涛
 * 创建时间：2017/5/27 16:30
 * 修改人：刘进涛
 * 修改时间：2017/5/27 16:30
 * 修改备注：
 */

@XStreamAlias("updateApps")
public class UpdateApps {
    // 设置为XML 的 attribute
    @XStreamAsAttribute()
    List<UpdateAppItem> updateAppItems;
    @XStreamAsAttribute()
    WhiteList whiteList;

    public UpdateApps() {
    }

    public UpdateApps(List<UpdateAppItem> updateAppItems) {
        this.updateAppItems = updateAppItems;
    }

    public List<UpdateAppItem> getUpdateAppItems() {
        return updateAppItems;
    }

    public void setUpdateAppItems(List<UpdateAppItem> updateAppItems) {
        this.updateAppItems = updateAppItems;
    }
}
