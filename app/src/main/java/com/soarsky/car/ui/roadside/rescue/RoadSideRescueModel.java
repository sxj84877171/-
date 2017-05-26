package com.soarsky.car.ui.roadside.rescue;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideCarTypeParam;
import com.soarsky.car.bean.RoadSideRescueInfo;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：申请救援model<br>
 * 该类为 RoadSideRescueModel<br>
 */


public class RoadSideRescueModel implements BaseModel{

    /**
     *获取车辆类型
     * @return ResponseDataBean<List<RoadSideCarTypeParam>> 获取车辆类型
     */
    public Observable<ResponseDataBean<List<RoadSideCarTypeParam>>> getCarType(){
        return api.getCarType().compose(RxSchedulers.<ResponseDataBean<List<RoadSideCarTypeParam>>>io_main());
    }

    /**
     * 上传救援申请
     * @param param 救援申请
     * @return ResponseDataBean<RoadSideRescueInfo> 参数
     */
    public Observable<ResponseDataBean<RoadSideRescueInfo>> uploadResouse(RoadSideRescueSendParam param){
        return api.uploadResouse(param.getCarnnum(),param.getCtype(),param.getLocation(),param.getPhone(),param.getServerItem()).compose(RxSchedulers.<ResponseDataBean<RoadSideRescueInfo>>io_main());
    }


}
