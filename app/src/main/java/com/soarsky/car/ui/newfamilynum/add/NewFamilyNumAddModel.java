package com.soarsky.car.ui.newfamilynum.add;

import android.app.Activity;
import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.local.db.FamilyNumDb;
import com.soarsky.car.data.local.db.bean.FamilyNum;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/15
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class NewFamilyNumAddModel implements BaseModel{


    /**
     * 亲情表封装对象
     */
    private FamilyNumDb familyNumDb;

    /**
     * appliction
     */
    private App app;
    /**
     * 上下文本
     */
    private Context context;

    /**
     * 设置文本
     * @param context 文本
     */
    public void setContext(Activity context){

        this.context = context;
        familyNumDb = new FamilyNumDb(context);
        familyNumDb.getFamilyNumDao();
        familyNumDb.getFamilyNumQuery();
        app= (App) context.getApplicationContext();

    }



    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号
     * @return Observable<FamilyNum> 其结果
     */
    public Observable<FamilyNum> insertFamilyNum(FamilyNum familyNum){
        return familyNumDb.insertData(familyNum);
    }

}
