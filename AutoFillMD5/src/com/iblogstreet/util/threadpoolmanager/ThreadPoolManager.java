package com.iblogstreet.util.threadpoolmanager;

/**
 * 类描述：线程池管理类
 * 创建人：王军
 * 创建时间：2017/10/23
 */

public class ThreadPoolManager {
    static volatile ThreadPoolProxy channelSaveXmlThreadPoolProxy;


    /**
     * 得到处理学生端线程池代理对象channelGroupStudentThreadPoolProxy
     */
    public static ThreadPoolProxy getChannelSaveXmlThreadPoolProxy() {
        if (channelSaveXmlThreadPoolProxy == null) {
            synchronized (ThreadPoolManager.class) {
                if (channelSaveXmlThreadPoolProxy == null) {
                    channelSaveXmlThreadPoolProxy = new ThreadPoolProxy(3, 10);
                }
            }
        }
        return channelSaveXmlThreadPoolProxy;
    }


}
