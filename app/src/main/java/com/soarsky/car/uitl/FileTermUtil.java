package com.soarsky.car.uitl;

import com.soarsky.car.bean.TermDataBean;
import com.soarsky.car.data.remote.server1.ApiF;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/14
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能： 终端工具类
 * 该类为
 */


public class FileTermUtil {

    public static void saveFile(TermDataBean bean){
        if(bean.getParams().getPath()!=null){
            ApiF.getInstance().getService().loadFile(bean.getParams().getPath()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        AppUtils.writeResponseBodyToDisk(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }
}
