package com.soarsky.car.ui.blindterm;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

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
 * 程序功能：绑定终端model <br>
 * 该类为 BlindTermModel <br>
 */


public class BlindTermModel implements BaseModel{



    /**
     * 绑定智能终端
     * @param carnum 车牌号
     * @param username 用户名
     * @param vCode 验证码
     * @return ResponseDataBean<String> 返回的结果
     */
    public Observable<ResponseDataBean<String>> bindIllegal(String carnum,String username,String vCode,String phone,String code,String ptype){
        return api.bindIllegal(carnum, username, vCode, phone, code, ptype).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }


    /**
     * 短信验证
     * @param phone 电话
     * @param ctype  0:伴君行注册 1驾照绑定App 2 终端绑定App
     * @return
     */
    public Observable<ResponseDataBean<Void>> sendsms(String phone,String ctype){
        return api.sendsms(phone,ctype).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

    /**
     * 绑定智能终端
     * @param carnum
     * @param phone
     * @param vin
     * @param vcode
     * @param username
     * @return
     */
    public Observable<ResponseDataBean<String>> bindTermianl(String carnum,String phone,String vin,String vcode,String username){
        return api.bindTermianl(carnum,phone,vin,vcode,username).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }

}
