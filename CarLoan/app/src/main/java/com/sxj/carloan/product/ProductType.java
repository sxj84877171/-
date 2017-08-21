package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/22.
 */

public abstract class ProductType implements IProductType {

    public double roundDouble1(double d){
        if(((long)d * 10) % 10 == 0){
            d += 0.1 ;
        }
        d = ((long)(d * 10)) / 10.0 ;
        return d;
    }

    public double roundDouble(double d){
        if(((long)(d * 10)) % 10 != 0){
            d += 1 ;
        }
        d = ((long)d) ;
        return d;
    }
}
