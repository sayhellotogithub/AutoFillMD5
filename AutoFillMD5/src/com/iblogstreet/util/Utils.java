package com.iblogstreet.util;/**
 * Created by Administrator on 2018/2/6.
 */

import com.iblogstreet.model.ApkInfo;
import com.iblogstreet.model.ConfigXmlBean;
import com.iblogstreet.model.UpdateAppItem;
import com.iblogstreet.model.UpdateApps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：CJPAD-C-V4.0
 * 类描述：
 * 创建人：Administrator王军
 * 创建时间：2018/2/6
 */
public class Utils {
    /**
     * 得到url
     *
     * @param url
     * @param fileName
     * @return
     */
    public static String getUrl(String url, String fileName) {
        int lastIndexOf = url.lastIndexOf("/");
        if (lastIndexOf != -1) {
            return url.substring(0, lastIndexOf) + "/" + fileName;
        }
        return "";
    }

    public static UpdateAppItem getUpdateAppItem(UpdateApps updateApps, String packageName) {
        if (null != updateApps) {
            List<UpdateAppItem> updateAppItems = updateApps.getUpdateAppItems();
            if (null != updateAppItems) {
                for (int i = 0; i < updateAppItems.size(); i++) {
                    if (updateAppItems.get(i).getPackageName().equals(packageName)) {
                        return updateAppItems.get(i);
                    }
                }
            }
        }
        return null;
    }

    public static UpdateApps readXml(String file) {
        UpdateApps updateApps = null;
        try {
            updateApps = XmlUtils.toBeanFromFile(file, UpdateApps.class);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return updateApps;
    }

    public static UpdateApps readXml(File file) {
        UpdateApps updateApps = null;
        try {
            updateApps = XmlUtils.toBeanFromFile(file, UpdateApps.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateApps;
    }

    public static List<ConfigXmlBean> readXmlFiles(List<File> xmlFiles) {
        List<ConfigXmlBean> updateAppsList = new ArrayList<>();
        if (null == xmlFiles || xmlFiles.size() > 0) {
            for (int i = 0; i < xmlFiles.size(); i++) {
                UpdateApps updateApps = readXml(xmlFiles.get(i));
                if (null != updateApps) {
                    updateAppsList.add(new ConfigXmlBean(xmlFiles.get(i).getName(), updateApps));
                } else {
                    System.out.println("读取配制文件" + xmlFiles.get(i).getName() + "出错，无法转换");
                }
            }
        }
        return updateAppsList;
    }


    /**
     * 读文件列表
     *
     * @return
     */
    public static List<File> readFileList() {
        return readFileList(".");
    }

    /**
     * 读文件列表
     *
     * @return
     */
    public static List<File> readFileList(String path) {
        File file = new File(path);
        List<File> fileList = new ArrayList<>();
        File[] files = file.listFiles(new FilterApk());
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                System.out.println("" + files[i].getName());
                fileList.add(files[i]);
            }
        }
        return fileList;
    }

    /**
     * 读文件列表
     *
     * @return
     */
    public static List<File> readXmlFileList() {
        File file = new File(".");
        List<File> fileList = new ArrayList<>();
        File[] files = file.listFiles(new FilterXml());
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                System.out.println("" + files[i].getName());
                fileList.add(files[i]);
            }
        }
        return fileList;
    }

    /**
     * 过滤文件
     * 如果已经存在相同的，文件，则进行过滤
     * 过滤规则：1.根据时间，2.根据
     *
     * @param fileList
     */
    public static List<ApkInfo> filterFile(List<File> fileList) {
        List<ApkInfo> apkInfoList = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            ApkInfo apkInfo = ApkUtil.getApkInfos(fileList.get(i));
            apkInfo.setApkFile(fileList.get(i));
            addApkInfoToList(apkInfoList, apkInfo);
        }
        return apkInfoList;
    }

    /**
     * apkInfo添加进List
     *
     * @param apkInfos
     * @param apkInfo
     */
    public static void addApkInfoToList(List<ApkInfo> apkInfos, ApkInfo apkInfo) {
        if (apkInfos.size() == 0) {
            apkInfos.add(apkInfo);
            return;
        }
        boolean isExist = false;
        for (int i = 0; i < apkInfos.size(); i++) {
            ApkInfo tempApkInfo = apkInfos.get(i);
            if (tempApkInfo.getApkPackage().equals(apkInfo.getApkPackage())
                    && apkInfo.getVersionCode() > tempApkInfo.getVersionCode()) {
                apkInfos.set(i, apkInfo);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            apkInfos.add(apkInfo);
        }
    }

    /**
     * 获得时间
     *
     * @param file
     * @return
     */
    public static FileTime getFileCreateTime(File file) {
        BasicFileAttributes bAttributes = null;
        try {
            bAttributes = Files.readAttributes(file.toPath(),
                    BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bAttributes.lastModifiedTime();
    }

    /**
     * 生成对应的MD5
     */
    public static String readFileToMD5(File apkfile) {
        return MD5Util.getMD5String(MD5Util.getBytes(apkfile));
    }
}
