package com.iblogstreet.model;/**
 * Created by Administrator on 2018/2/2.
 */

import java.io.File;

/**
 * 项目名称：CJPAD-C-V4.0
 * 类描述：
 * 创建人：Administrator王军
 * 创建时间：2018/2/2
 */
public class ApkInfo {
    Long versionCode;
    String versionName;
    String apkPackage;
    String minSdkVersion;

    public File getApkFile() {
        return apkFile;
    }

    public void setApkFile(File apkFile) {
        this.apkFile = apkFile;
    }

    private File apkFile;

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public ApkInfo() {
    }

    public ApkInfo(Long versionCode, String versionName, String apkPackage, String minSdkVersion) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.apkPackage = apkPackage;
        this.minSdkVersion = minSdkVersion;
    }

    public ApkInfo(Long versionCode, String versionName, String apkPackage, String minSdkVersion, File apkFile) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.apkPackage = apkPackage;
        this.minSdkVersion = minSdkVersion;
        this.apkFile = apkFile;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getApkPackage() {
        return apkPackage;
    }

    public void setApkPackage(String apkPackage) {
        this.apkPackage = apkPackage;
    }


}
