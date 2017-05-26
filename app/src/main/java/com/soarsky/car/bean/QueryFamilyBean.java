package com.soarsky.car.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 查询亲情号码<br>
 */

public class QueryFamilyBean implements Serializable {
    /**
     * 车牌
     */
    private String carnum;


    private List<FamilyNumIlistBean> ilist;

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public List<FamilyNumIlistBean> getIlist() {
        return ilist;
    }

    public void setIlist(List<FamilyNumIlistBean> ilist) {
        this.ilist = ilist;
    }
}
