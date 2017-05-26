package com.soarsky.car.ui.family.familynumconfirm;

import java.io.Serializable;

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
 * 程序功能：更改亲情号的实体类 <br>
 * 该类为 FamilyNumUpdateEntry <br>
 */

public class FamilyNumUpdateEntry implements Serializable{
    /**
     * 需要修改的号码
     */
    private String phoneNum;
    /**
     * 第几个要修改的号码 1、2、3
     */
    private int num;
    /**
     * 其他的不需要修改号码
     */
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
        /**
         * 号码一
         */
        private String otherPhoneOne;
        /**
         * 号码二
         */
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
