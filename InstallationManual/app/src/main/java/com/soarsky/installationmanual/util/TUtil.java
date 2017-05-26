package com.soarsky.installationmanual.util;

import java.lang.reflect.ParameterizedType;

/**
 * 安装手册
 * 作者： 孙向锦
 * 时间： 2017/02/09
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * MVP初始化的工具类
 */
public class TUtil {

    /***
     * 根据指定泛型类型，实例化指定泛型参数
     * @param o 具体引用对象
     * @param i 第几个泛型参数
     * @param <T> 泛型类型
     * @return 泛型对象
     */
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过类名来查找对应的类
     * @param className
     * @return
     */
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
