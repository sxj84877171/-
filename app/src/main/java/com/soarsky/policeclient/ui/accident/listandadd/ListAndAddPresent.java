package com.soarsky.policeclient.ui.accident.listandadd;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.AccidentBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.bean.AccidentDataBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析事故列表Present<br>
 */
public class ListAndAddPresent extends BasePresenter<ListAndAddModel,ListAndAddView> {
    @Override
    public void onStart() {

    }
    /**
     * 从服务器获取事故列表
     */
    public void getAccidentList(){

        mModel.getAccidentList().subscribe(new Subscriber<ResponseDataBean<List<AccidentBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(ResponseDataBean<List<AccidentBean>> accidentBean) {
                if (accidentBean !=null && accidentBean.getData()!=null && !accidentBean.getData().isEmpty()){
                    List<AccidentDataBean.AccidentItemBean> accidentItemBeanList = new ArrayList<>();
                    for (AccidentBean accidentItem : accidentBean.getData()) {
                        AccidentDataBean.AccidentItemBean accidentItemBean = new AccidentDataBean.AccidentItemBean();
                        accidentItemBean.setTime(accidentItem.getAtime());
                        accidentItemBean.setWeizhi(accidentItem.getLocation());
                        accidentItemBean.setId(accidentItem.getId());
                        accidentItemBeanList.add(accidentItemBean);
                    }
                    mView.showData(accidentItemBeanList);
                }else {
                    mView.onNoData();
                }
            }
        });
    }
}
