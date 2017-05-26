package com.soarsky.car.uitl;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/14
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能： 终端的一些数据解析
 * 该类为
 */


public class TerminalUtils {

    public static String toCarType(int cartype){
        if(cartype==1){
            return "P";
        }else if(cartype==2){
            return "N";
        }
        else if(cartype==3){
            return "M";
        }
        else if(cartype==4){
            return "F";
        }
        else if(cartype==5){
            return "E";
        }
        else if(cartype==6){
            return "D";
        }
        else if(cartype==7){
            return "C5";
        }
        else if(cartype==8){
            return "C4";
        }
        else if(cartype==9){
            return "C3";
        }
        else if(cartype==10){
            return "C2";
        }
        else if(cartype==11){
            return "C1";
        }
        else if(cartype==12){
            return "B2";
        }
        else if(cartype==13){
            return "B1";
        }
        else if(cartype==14){
            return "A3";
        }
        else if(cartype==15){
            return "A2";
        }
        else if(cartype==16){ return "A1";}

        return "C1";
    }

    public static int toCarType(String cartype){
        if(cartype.equals("P")){
            return 1;
        }else if(cartype.equals("N")){
            return 2;
        }
        else if(cartype.equals("M")){
            return 3;
        }
        else if(cartype.equals("F")){
            return 4;
        }
        else if(cartype.equals("E")){
            return 5;
        }
        else if(cartype.equals("D")){
            return 6;
        }
        else if(cartype.equals("C5")){
            return 7;
        }
        else if(cartype.equals("C4")){
            return 8;
        }
        else if(cartype.equals("C3")){
            return 9;
        }
        else if(cartype.equals("C2")){
            return 10;
        }
        else if(cartype.equals("C1")){
            return 11;
        }else if(cartype.equals("B2")){
            return 12;
        }
        else if(cartype.equals("B1")){
            return 13;
        }else if(cartype.equals("A3")){
            return 14;
        }
        else if(cartype.equals("A2")){
            return 15;
        }else if(cartype.equals("A1")){
            return 16;
        }
        return 11;
    }
}
