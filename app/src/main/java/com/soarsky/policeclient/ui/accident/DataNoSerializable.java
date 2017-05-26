package com.soarsky.policeclient.ui.accident;

import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.Car;

import java.util.ArrayList;

/**
 * android_police_app
 * 作者： 魏凯
 * 时间： 2017/5/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */
public class DataNoSerializable {

    private DataNoSerializable(){}

    private static DataNoSerializable dataNoSerializable;

    public static DataNoSerializable getDataNoSerializable() {
        if(dataNoSerializable==null){
            dataNoSerializable = new DataNoSerializable();
            return dataNoSerializable;
        }else {
            return dataNoSerializable;
        }
    }

    private ArrayList<Car> cars;

    private Car car;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    private AccidentDataBean.AccidentItemBean accidentItemBean;

    public AccidentDataBean.AccidentItemBean getAccidentItemBean() {
        return accidentItemBean;
    }

    public void setAccidentItemBean(AccidentDataBean.AccidentItemBean accidentItemBean) {
        this.accidentItemBean = accidentItemBean;
    }

    private AccidentDataBean.AccidentItemBean accidentItemBean1;

    public AccidentDataBean.AccidentItemBean getAccidentItemBean1() {
        return accidentItemBean1;
    }

    public void setAccidentItemBean1(AccidentDataBean.AccidentItemBean accidentItemBean1) {
        this.accidentItemBean1 = accidentItemBean1;
    }


    private AccidentDataBean.AccidentItemBean updateAccidentItemBean;

    public AccidentDataBean.AccidentItemBean getUpdateAccidentItemBean() {
        return updateAccidentItemBean;
    }

    public void setUpdateAccidentItemBean(AccidentDataBean.AccidentItemBean updateAccidentItemBean) {
        this.updateAccidentItemBean = updateAccidentItemBean;
    }

    private AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean;

    public AccidentDataBean.AccidentItemBean.AccidentCarBean getAccidentCarBean() {
        return accidentCarBean;
    }

    public void setAccidentCarBean(AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean) {
        this.accidentCarBean = accidentCarBean;
    }
}
