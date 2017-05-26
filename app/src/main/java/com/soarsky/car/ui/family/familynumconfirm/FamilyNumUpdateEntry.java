package com.soarsky.car.ui.family.familynumconfirm;

import java.io.Serializable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class FamilyNumUpdateEntry implements Serializable{

    private String phoneNum;

    private int num;

    private OtherPhone otherPhone;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public OtherPhone getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(OtherPhone otherPhone) {
        this.otherPhone = otherPhone;
    }

    public static class OtherPhone implements Serializable{

        private String otherPhoneOne;

        private String getOtherPhoneTwo;

        public String getOtherPhoneOne() {
            return otherPhoneOne;
        }

        public void setOtherPhoneOne(String otherPhoneOne) {
            this.otherPhoneOne = otherPhoneOne;
        }

        public String getGetOtherPhoneTwo() {
            return getOtherPhoneTwo;
        }

        public void setGetOtherPhoneTwo(String getOtherPhoneTwo) {
            this.getOtherPhoneTwo = getOtherPhoneTwo;
        }
    }

}
