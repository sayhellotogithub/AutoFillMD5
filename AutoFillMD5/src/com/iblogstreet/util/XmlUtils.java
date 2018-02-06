package com.iblogstreet.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

/**
 * Xml解析类
 */
public class XmlUtils {

    private final static String TAG = XmlUtils.class.getSimpleName();

    /**
     * 将一个xml流转换为bean实体类
     *
     * @param type
     * @param is
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(Class<T> type, InputStream is) {
        XStream xmStream = new XStream(new DomDriver("UTF-8"));
        // 设置可忽略为在javabean类中定义的界面属性
        xmStream.ignoreUnknownElements();
        xmStream.registerConverter(new MyIntCoverter());
        xmStream.registerConverter(new MyLongCoverter());
        xmStream.registerConverter(new MyFloatCoverter());
        xmStream.registerConverter(new MyDoubleCoverter());
        xmStream.processAnnotations(type);
        T obj = null;
        try {
            obj = (T) xmStream.fromXML(is);
        } catch (Exception e) {
            System.out.println("解析xml发生异常：" + e.getMessage());
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("关闭流出现异常：" + e.getMessage());
                }
            }
        }
        return obj;
    }

    public static <T> T toBean(Class<T> type, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return toBean(type, new ByteArrayInputStream(bytes));
    }

    public static String toXml(Object obj) {
        XStream xstream = new XStream(new StaxDriver());
        //            XStream xstream=new XStream(new DomDriver()); //直接用jaxp dom来解释
        //   XStream xstream = new XStream(new DomDriver("UTF-8")); //指定编码解析器,直接用jaxp dom来解释

        ////如果没有这句，xml中的根元素会是<包.类名>；或者说：注解根本就没生效，所以的元素名就是类的属性
        xstream.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
        return xstream.toXML(obj);
    }

    /**
     * 写到xml文件中去
     *
     * @param obj      对象
     * @param absPath  绝对路径
     * @param fileName 文件名
     * @return boolean
     * @Title: writeXMLFile
     * @Description: TODO
     */

    public static boolean toXMLFile(Object obj, String absPath, String fileName) {
        String strXml = toXml(obj);
        String filePath = absPath + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("创建{" + filePath + "}文件失败!!!" + e.getMessage());
                return false;
            }
        }// end if
        OutputStream ous = null;
        try {
            ous = new FileOutputStream(file);
            ous.write(strXml.getBytes("utf-8"));
            ous.flush();
        } catch (Exception e) {
            System.out.println("写{" + filePath + "}文件失败!!!" + e.getMessage());
            return false;
        } finally {
            if (ous != null) {
                try {
                    ous.close();
                } catch (IOException e) {
                    System.out.println("写{" + filePath + "}文件关闭输出流异常!!!" + e.getMessage());
                }
            }
        }
        return true;
    }

    /**
     * 从xml文件读取报文
     *
     * @param absPath  绝对路径
     * @param fileName 文件名
     * @param cls
     * @return T
     * @throws Exception
     * @Title: toBeanFromFile
     * @Description: TODO
     */
    public static <T> T toBeanFromFile(String absPath, String fileName, Class<T> cls) throws Exception {
        String filePath = absPath + fileName;
        InputStream ins = null;
        try {
            ins = new FileInputStream(new File(filePath));
        } catch (Exception e) {
            throw new Exception("读{" + filePath + "}文件失败！", e);
        }

        String encode = useEncode(cls);
        XStream xstream = new XStream(new DomDriver(encode));
        xstream.processAnnotations(cls);
        T obj = null;
        try {
            obj = (T) xstream.fromXML(ins);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("解析{" + filePath + "}文件失败！", e);
        }
        if (ins != null)
            ins.close();
        return obj;
    }

    public static <T> T toBeanFromFile(String filePath, Class<T> cls) throws Exception {
        InputStream ins = null;
        try {
            ins = new FileInputStream(new File(filePath));
        } catch (Exception e) {
            throw new Exception("读{" + filePath + "}文件失败！", e);
        }

        String encode = useEncode(cls);
        XStream xstream = new XStream(new DomDriver(encode));
        xstream.processAnnotations(cls);
        T obj = null;
        try {
            obj = (T) xstream.fromXML(ins);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("解析{" + filePath + "}文件失败！", e);
        }
        if (ins != null)
            ins.close();
        return obj;
    }

    public static <T> T toBeanFromFile(File file, Class<T> cls) throws Exception {
        InputStream ins = null;
        try {
            ins = new FileInputStream(file);
        } catch (Exception e) {
            throw new Exception("读{" + file.getAbsolutePath() + "}文件失败！", e);
        }

        String encode = useEncode(cls);
        XStream xstream = new XStream(new DomDriver(encode));
        xstream.processAnnotations(cls);
        T obj = null;
        try {
            obj = (T) xstream.fromXML(ins);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new Exception("解析{" + file.getAbsolutePath() + "}文件失败！", e);
        }
        if (ins != null)
            ins.close();
        return obj;
    }

    public static String useEncode(Class cls) {
        return "utf-8";
    }


    private static class MyIntCoverter extends IntConverter {

        @Override
        public Object fromString(String str) {
            int value;
            try {
                value = (Integer) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyLongCoverter extends LongConverter {
        @Override
        public Object fromString(String str) {
            long value;
            try {
                value = (Long) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyFloatCoverter extends FloatConverter {
        @Override
        public Object fromString(String str) {
            float value;
            try {
                value = (Float) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyDoubleCoverter extends DoubleConverter {
        @Override
        public Object fromString(String str) {
            double value;
            try {
                value = (Double) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }
}
