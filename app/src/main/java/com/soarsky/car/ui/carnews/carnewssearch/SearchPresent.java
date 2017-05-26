package com.soarsky.car.ui.carnews.carnewssearch;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.AutomotiveInfo;

import java.util.List;

import rx.Subscriber;

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
 * 该类为 汽车资讯搜索功能P层<br>
 */

public class SearchPresent extends BasePresenter<SearchModel,SearchView>{
    @Override
    public void onStart() {

    }

    /**
     * 根据类型、关键字进行搜索
     * @param type 文章类型，1、汽车保养，2、交通安全，3、交通法规
     * @param keyword 搜索关键字
     */
    public void search(int type,String keyword){
        mModel.getAutomotiveList(type,keyword).subscribe(new Subscriber<ResponseDataBean<List<AutomotiveInfo>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.searchError(e);
            }

            @Override
            public void onNext(ResponseDataBean<List<AutomotiveInfo>> listResponseDataBean) {
                if (SUCCESS_FLAG.equals(listResponseDataBean.getStatus())){
                        mView.searchSuccess(listResponseDataBean);
                }
            }
        });
    }
}
