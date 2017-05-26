package com.soarsky.car.uitl;

import static com.soarsky.car.Constants.NO_CODE;
import static com.soarsky.car.Constants.NUM_EIGHTTEEN_CODE;
import static com.soarsky.car.Constants.NUM_EIGHTTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_EIGHT_CODE;
import static com.soarsky.car.Constants.NUM_EIGHT_TYPE;
import static com.soarsky.car.Constants.NUM_ELEVEN_CODE;
import static com.soarsky.car.Constants.NUM_ELEVEN_TYPE;
import static com.soarsky.car.Constants.NUM_FIFTEEN_CODE;
import static com.soarsky.car.Constants.NUM_FIFTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_FIVE_CODE;
import static com.soarsky.car.Constants.NUM_FIVE_TYPE;
import static com.soarsky.car.Constants.NUM_FOURTEEN_CODE;
import static com.soarsky.car.Constants.NUM_FOURTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_FOUR_CODE;
import static com.soarsky.car.Constants.NUM_FOUR_TYPE;
import static com.soarsky.car.Constants.NUM_NIGHTTEEN_CODE;
import static com.soarsky.car.Constants.NUM_NIGHTTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_NIGHT_CODE;
import static com.soarsky.car.Constants.NUM_NIGHT_TYPE;
import static com.soarsky.car.Constants.NUM_ONE_CODE;
import static com.soarsky.car.Constants.NUM_ONE_TYPE;
import static com.soarsky.car.Constants.NUM_SEVENTEEN_CODE;
import static com.soarsky.car.Constants.NUM_SEVENTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_SEVEN_CODE;
import static com.soarsky.car.Constants.NUM_SEVEN_TYPE;
import static com.soarsky.car.Constants.NUM_SIXTEEN_CODE;
import static com.soarsky.car.Constants.NUM_SIXTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_SIX_CODE;
import static com.soarsky.car.Constants.NUM_SIX_TYPE;
import static com.soarsky.car.Constants.NUM_TEN_CODE;
import static com.soarsky.car.Constants.NUM_TEN_TYPE;
import static com.soarsky.car.Constants.NUM_THIRTEEN_CODE;
import static com.soarsky.car.Constants.NUM_THIRTEEN_TYPE;
import static com.soarsky.car.Constants.NUM_THREE_CODE;
import static com.soarsky.car.Constants.NUM_THREE_TYPE;
import static com.soarsky.car.Constants.NUM_TWELVE_CODE;
import static com.soarsky.car.Constants.NUM_TWELVE_TYPE;
import static com.soarsky.car.Constants.NUM_TWO_CODE;
import static com.soarsky.car.Constants.NUM_TWO_TYPE;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2017/5/5
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为车辆类型
 */

public class CarTypeUtil {
    public static String getCarType(String num){

        if (NUM_ONE_CODE.equals(num)){
            return NUM_ONE_TYPE;
        }else if (NUM_TWO_CODE.equals(num)){
            return NUM_TWO_TYPE;
        }else if (NUM_THREE_CODE.equals(num)){
            return NUM_THREE_TYPE;
        }else if (NUM_FOUR_CODE.equals(num)){
            return NUM_FOUR_TYPE;
        }else if (NUM_FIVE_CODE.equals(num)){
            return NUM_FIVE_TYPE;
        }else if (NUM_SIX_CODE.equals(num)){
            return NUM_SIX_TYPE;
        }else if (NUM_SEVEN_CODE.equals(num)){
            return NUM_SEVEN_TYPE;
        }else if (NUM_EIGHT_CODE.equals(num)){
            return NUM_EIGHT_TYPE;
        }else if (NUM_NIGHT_CODE.equals(num)){
            return NUM_NIGHT_TYPE;
        }else if (NUM_TEN_CODE.equals(num)){
            return NUM_TEN_TYPE;
        }else if (NUM_ELEVEN_CODE.equals(num)){
            return NUM_ELEVEN_TYPE;
        }else if (NUM_TWELVE_CODE.equals(num)){
            return NUM_TWELVE_TYPE;
        }else if (NUM_THIRTEEN_CODE.equals(num)){
            return NUM_THIRTEEN_TYPE;
        }else if (NUM_FOURTEEN_CODE.equals(num)){
            return NUM_FOURTEEN_TYPE;
        }else if (NUM_FIFTEEN_CODE.equals(num)){
            return NUM_FIFTEEN_TYPE;
        }else if (NUM_SIXTEEN_CODE.equals(num)){
            return NUM_SIXTEEN_TYPE;
        }else if (NUM_SEVENTEEN_CODE.equals(num)){
            return NUM_SEVENTEEN_TYPE;
        }else if (NUM_EIGHTTEEN_CODE.equals(num)){
            return NUM_EIGHTTEEN_TYPE;
        }else if (NUM_NIGHTTEEN_CODE.equals(num)){
            return NUM_NIGHTTEEN_TYPE;
        }
        return num;
    }
}
