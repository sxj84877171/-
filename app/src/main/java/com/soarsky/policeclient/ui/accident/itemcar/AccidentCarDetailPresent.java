package com.soarsky.policeclient.ui.accident.itemcar;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.ui.accident.serverbean.WeizhangBean;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析车辆详情Present<br>
 */
public class AccidentCarDetailPresent extends BasePresenter<AccidentCarDetailModel,AccidentCarDetailView> {
    @Override
    public void onStart() {

    }

    /**
     * 从服务器根据车牌号查询违章信息
     * @param ssid 车辆的车牌号
     */
    public void WeizhangQueryFromServer(String ssid){
        mModel.WeizhangQueryFromServer(ssid).subscribe(new Subscriber<ResponseDataBean<WeizhangBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(ResponseDataBean<WeizhangBean> weizhangBean) {
                if(weizhangBean!=null && weizhangBean.getData()!=null && weizhangBean.getData().getIlist()!=null && !weizhangBean.getData().getIlist().isEmpty()){
                    ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem> weizhangItems = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem>();
                    for (WeizhangBean.WeizhangItem weizhangItem:weizhangBean.getData().getIlist()) {
                        AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem item = new AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem();
                        item.setTime(weizhangItem.getStime());
                        item.setDizhi(weizhangItem.getAddress());
                        item.setYuanyin(weizhangItem.getInf());
                        weizhangItems.add(item);
                    }
                    mView.showData(weizhangItems);
                }else {
                    mView.onNoData();
                }

            }
        });
    }
}
