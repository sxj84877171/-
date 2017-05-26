package com.soarsky.car.base;

import android.app.Service;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * Service 基类
 */
public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
