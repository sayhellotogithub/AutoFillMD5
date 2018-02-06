package com.iblogstreet.util;


import com.iblogstreet.model.ApkInfo;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.UseFeature;
import org.jdom2.Namespace;

import java.io.File;
import java.io.IOException;

public class ApkUtil {

    private static final Namespace NS = Namespace.getNamespace("http://schemas.android.com/apk/res/android");


    public static ApkInfo getApkInfos(String apkPath) {
        ApkInfo apkInfo = new ApkInfo();
        try {
            try (ApkFile apkFile = new ApkFile(new File(apkPath))) {
                ApkMeta apkMeta = apkFile.getApkMeta();
                System.out.println(apkMeta.getLabel());
                System.out.println(apkMeta.getPackageName());
                System.out.println(apkMeta.getVersionCode());
                apkInfo.setApkPackage(apkMeta.getPackageName());
                apkInfo.setVersionCode(apkMeta.getVersionCode());
                apkInfo.setVersionName(apkMeta.getVersionName());
                for (UseFeature feature : apkMeta.getUsesFeatures()) {
                    System.out.println(feature.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apkInfo;
    }

    public static ApkInfo getApkInfos(File file) {
        ApkInfo apkInfo = new ApkInfo();
        try {
            try (ApkFile apkFile = new ApkFile(file)) {
                ApkMeta apkMeta = apkFile.getApkMeta();
                System.out.println(apkMeta.getLabel());
                System.out.println(apkMeta.getPackageName());
                System.out.println(apkMeta.getVersionCode());
                apkInfo.setApkPackage(apkMeta.getPackageName());
                apkInfo.setVersionCode(apkMeta.getVersionCode());
                apkInfo.setVersionName(apkMeta.getVersionName());
                for (UseFeature feature : apkMeta.getUsesFeatures()) {
                    System.out.println(feature.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apkInfo;
    }
}  