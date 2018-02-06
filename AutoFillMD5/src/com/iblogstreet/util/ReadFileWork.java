package com.iblogstreet.util;/**
 * Created by Administrator on 2018/2/6.
 */

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.iblogstreet.util.Utils.readFileList;
import static com.iblogstreet.util.Utils.readXmlFileList;

/**
 * 项目名称：CJPAD-C-V4.0
 * 类描述：
 * 创建人：王军
 * 创建时间：2018/2/6
 */
public class ReadFileWork extends Thread {
    CountDownLatch mCountDownLatch;
    List<File> mFileList;
    boolean mIsXml;

    public ReadFileWork(CountDownLatch countDownLatch, List<File> fileList, boolean isXml) {
        this.mFileList = fileList;
        this.mCountDownLatch = countDownLatch;
        this.mIsXml = isXml;
    }

    @Override
    public void run() {
        super.run();
        if (mIsXml) {
            mFileList.addAll(readXmlFileList());
        } else {
            mFileList.addAll(readFileList());
        }
        mCountDownLatch.countDown();

    }
}
