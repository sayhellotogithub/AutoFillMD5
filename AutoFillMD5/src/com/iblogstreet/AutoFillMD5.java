package com.iblogstreet;/**
 * Created by Administrator on 2018/2/2.
 */

import com.iblogstreet.model.ApkInfo;
import com.iblogstreet.model.ConfigXmlBean;
import com.iblogstreet.model.UpdateAppItem;
import com.iblogstreet.model.UpdateApps;
import com.iblogstreet.util.ReadFileWork;
import com.iblogstreet.util.XmlUtils;
import com.iblogstreet.util.threadpoolmanager.ThreadPoolManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.iblogstreet.util.Utils.*;

/**
 * 项目名称：CJPAD-C-V4.0
 * 类描述：自动读取xml配制文件,并填充相关的属性
 * 创建人：王军
 * 创建时间：2018/2/2
 */
public class AutoFillMD5 {

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println("开始时间：" + startTime);
        System.out.println("读取当前目录下的文件");
        //读取apk
        List<File> apkFileList = new ArrayList<>();
        //读取xml
        List<File> xmlFileList = new ArrayList<>();
        //异步读取文件
        new ReadFileWork(countDownLatch, apkFileList, false).start();
        new ReadFileWork(countDownLatch, xmlFileList, true).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //只有当文件都存在，才进行读取
        if (null != apkFileList && apkFileList.size() > 0 && null != xmlFileList && xmlFileList.size() > 0) {
            //得到过滤之后的ApkInfoList
            List<ApkInfo> apkInfoList = filterFile(apkFileList);
            List<ConfigXmlBean> xmlInfoList = readXmlFiles(xmlFileList);

            if (xmlInfoList == null || xmlInfoList.size() == 0 || apkInfoList == null || apkInfoList.size() == 0) {
                System.out.println("未找到符合的列表");
                return;
            }
            //修改配制文件
            //比对，并生成相关的MD5
            for (int i = 0; i < xmlInfoList.size(); i++) {
                UpdateApps updateApps = xmlInfoList.get(i).getUpdateApps();
                //同时循环app列表及配制文件列表，填充相关的数据，并生成相关的数据
                List<UpdateAppItem> updateAppItemList = updateApps.getUpdateAppItems();
                String fileName = xmlInfoList.get(i).getFileName();
                boolean isModify = false;
                if (updateAppItemList != null) {
                    for (int j = 0; j < updateAppItemList.size(); j++) {
                        UpdateAppItem updateAppItem = updateAppItemList.get(j);
                        for (ApkInfo apkInfo : apkInfoList) {
                            if (apkInfo.getApkPackage().equals(updateAppItem.getPackageName())) {
                                if (apkInfo.getVersionCode() > Long.parseLong(updateAppItem.getVersion())) {
                                    //生成MD5
                                    String md5 = readFileToMD5((apkInfo.getApkFile()));
                                    System.out.println(apkInfo.getApkPackage() + ",MD5:" + md5);
                                    updateAppItem.setMd5(md5);
                                    updateAppItem.setVersion(apkInfo.getVersionCode() + "");
                                    updateAppItem.setVersionString(apkInfo.getVersionName());
                                    updateAppItem.setUrl(getUrl(updateAppItem.getUrl(), apkInfo.getApkFile().getName()));
                                    isModify = true;
                                }
                            }
                        }
                    }
                    if (isModify) {
                        //调用线程生成对应的xml
                        ThreadPoolManager.getChannelSaveXmlThreadPoolProxy().execute(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(fileName + "生成时间：" + startTime);
                                boolean success = XmlUtils.toXMLFile(updateApps, "./", fileName);
                                if (success) {
                                    System.out.println(fileName + "生成xml成功");
                                } else {
                                    System.out.println(fileName + "生成xml失败");
                                }
                                System.out.println(fileName + "完成时间：" + startTime);
                            }
                        });
                    }
                }

            }


        }
    }


}
