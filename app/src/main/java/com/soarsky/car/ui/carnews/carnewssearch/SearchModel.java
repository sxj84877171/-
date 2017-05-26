package com.soarsky.car.ui.carnews.carnewssearch;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  汽车资讯--搜索功能逻辑层<br>
 */

public class SearchModel implements BaseModel {

    /**
     * 根据关键字进行搜索
     * @param type  文章类型，1、汽车保养，2、交通安全，3、交通法规
     * @param keyword 关键字
     * @return 资讯集合
     */
  public Observable<ResponseDataBean<List<AutomotiveInfo>>> getAutomotiveList(int type,String keyword){
      return api.getAutomotiveList(type,keyword).compose(RxSchedulers.<ResponseDataBean<List<AutomotiveInfo>>>io_main());
  }
}
