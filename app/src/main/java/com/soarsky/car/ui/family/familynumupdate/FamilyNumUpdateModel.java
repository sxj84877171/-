package com.soarsky.car.ui.family.familynumupdate;

import android.app.Activity;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.family.FamilyNumDb;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：更改的列表的model <br>
 * 该类为 FamilyNumUpdateModel <br>
 */

public class FamilyNumUpdateModel implements BaseModel{
    /**
     * 上下文本
     */
    public Activity context;
    /**
     * 亲情号码封装接口的对象
     */
    private FamilyNumDb familyNumDb;

    /**
     * 设置文本
     * @param context 上下文本
     */
    public void setContext(Activity context){

        this.context = context;
        familyNumDb = new FamilyNumDb(context);
        familyNumDb.getFamilyNumDao();
        familyNumDb.getFamilyNumQuery();

    }



    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号
     * @return  FamilyNum  亲情号
     */
    public Observable<FamilyNum> insertFamilyNum(FamilyNum familyNum){
        return familyNumDb.insertData(familyNum);
    }

    /***
     * 清空亲情号表的所有数据
     * @return Void
     */
    public Observable<Void> deleteAll(){
        return familyNumDb.deleteAll();
    }

    /**
     * 查询所有亲情号码的数据
     * @return List<FamilyNum> 亲情列表
     */
    public Observable<List<FamilyNum>> getAllFamilyNumList(){
        return familyNumDb.getAllFamilyNumList();
    }

}
