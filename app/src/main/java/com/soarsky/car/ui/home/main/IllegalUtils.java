package com.soarsky.car.ui.home.main;

import com.soarsky.car.bean.ViolationDealIlist;

import java.util.ArrayList;
import java.util.List;

import static com.soarsky.car.Constants.REVOKED;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/25
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class IllegalUtils {


    public static int JudgeNotIllegal(List<ViolationDealIlist> list){

        int count = 0;
        List<ViolationDealIlist> _list = new ArrayList<ViolationDealIlist>();
        for(ViolationDealIlist violationDealIlist:list){
            if(!violationDealIlist.getStatus().equals(REVOKED)){
                _list.add(violationDealIlist);
            }
        }

        if(_list.size()>0){
            count = _list.size();
        }

        return count;

    }

}
