package com.sxj.carloan.util;

import com.sxj.carloan.bean.ServerBean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/8/20.
 */

public class BeanToMap {

    public static Map<String,Object> transRowsBean2Map(ServerBean.RowsBean bean){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            Field[] fields = ServerBean.RowsBean.class.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                String proName = field.getName();
                Object proValue = field.get(bean);
                if(proValue != null){
                    if(!"-1".equals(proValue.toString()) &&!"0".equals(proValue.toString()) &&(!"".equals(proValue.toString().trim()))){
                        if(!"serialVersionUID".equals(proName)){
                            map.put(proName, proValue);
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return map;

    }
}
