package com.soarsky.car.uitl;

import static com.soarsky.car.Constants.CAR_TYPE_A1;
import static com.soarsky.car.Constants.CAR_TYPE_A2;
import static com.soarsky.car.Constants.CAR_TYPE_A3;
import static com.soarsky.car.Constants.CAR_TYPE_B1;
import static com.soarsky.car.Constants.CAR_TYPE_B2;
import static com.soarsky.car.Constants.CAR_TYPE_C1;
import static com.soarsky.car.Constants.CAR_TYPE_C2;
import static com.soarsky.car.Constants.CAR_TYPE_C3;
import static com.soarsky.car.Constants.CAR_TYPE_C4;
import static com.soarsky.car.Constants.CAR_TYPE_C5;
import static com.soarsky.car.Constants.CAR_TYPE_D;
import static com.soarsky.car.Constants.CAR_TYPE_E;
import static com.soarsky.car.Constants.CAR_TYPE_F;
import static com.soarsky.car.Constants.CAR_TYPE_M;
import static com.soarsky.car.Constants.CAR_TYPE_N;
import static com.soarsky.car.Constants.CAR_TYPE_P;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能： 终端的一些数据解析<br>
 * 该类为<br>
 */


public class TerminalUtils {

    public static String toCarType(int cartype){
        if(cartype==1){
            return CAR_TYPE_P;
        }else if(cartype==2){
            return CAR_TYPE_N;
        }
        else if(cartype==3){
            return CAR_TYPE_M;
        }
        else if(cartype==4){
            return CAR_TYPE_F;
        }
        else if(cartype==5){
            return CAR_TYPE_E;
        }
        else if(cartype==6){
            return CAR_TYPE_D;
        }
        else if(cartype==7){
            return CAR_TYPE_C5;
        }
        else if(cartype==8){
            return CAR_TYPE_C4;
        }
        else if(cartype==9){
            return CAR_TYPE_C3;
        }
        else if(cartype==10){
            return CAR_TYPE_C2;
        }
        else if(cartype==11){
            return CAR_TYPE_C1;
        }
        else if(cartype==12){
            return CAR_TYPE_B2;
        }
        else if(cartype==13){
            return CAR_TYPE_B1;
        }
        else if(cartype==14){
            return CAR_TYPE_A3;
        }
        else if(cartype==15){
            return CAR_TYPE_A2;
        }
        else if(cartype==16){ return CAR_TYPE_A1;}

        return CAR_TYPE_C1;
    }

    public static int toCarType(String cartype){
        if(CAR_TYPE_P.equals(cartype)){
            return 1;
        }else if(CAR_TYPE_N.equals(cartype)){
            return 2;
        }
        else if(CAR_TYPE_M.equals(cartype)){
            return 3;
        }
        else if(CAR_TYPE_F.equals(cartype)){
            return 4;
        }
        else if(CAR_TYPE_E.equals(cartype)){
            return 5;
        }
        else if(CAR_TYPE_D.equals(cartype)){
            return 6;
        }
        else if(CAR_TYPE_C5.equals(cartype)){
            return 7;
        }
        else if(CAR_TYPE_C4.equals(cartype)){
            return 8;
        }
        else if(CAR_TYPE_C3.equals(cartype)){
            return 9;
        }
        else if(CAR_TYPE_C2.equals(cartype)){
            return 10;
        }
        else if(CAR_TYPE_C1.equals(cartype)){
            return 11;
        }else if(CAR_TYPE_B2.equals(cartype)){
            return 12;
        }
        else if(CAR_TYPE_B1.equals(cartype)){
            return 13;
        }else if(CAR_TYPE_A3.equals(cartype)){
            return 14;
        }
        else if(CAR_TYPE_A2.equals(cartype)){
            return 15;
        }else if(CAR_TYPE_A1.equals("A1")){
            return 16;
        }
        return 11;
    }
}
