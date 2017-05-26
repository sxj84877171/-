package com.soarsky.policeclient.base;

import android.app.Service;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为service基类<br>
 */
public abstract class BaseService extends Service {

    /**
     * Service初始化时会调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }


    /**
     * Service销毁时会调用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
