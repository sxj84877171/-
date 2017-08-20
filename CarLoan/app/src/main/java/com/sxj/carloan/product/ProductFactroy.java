package com.sxj.carloan.product;

import com.sxj.carloan.bean.ServerBean;

/**
 * Created by admin on 2017/8/20.
 */

public class ProductFactroy {

    private static ProductFactroy instance = new ProductFactroy();

    public static ProductFactroy getInstance() {
        return instance;
    }

    public IProductCalc getProductCale(int type, ServerBean.RowsBean bean){
        switch (type){
            case 1:
                return new ICBC36ProductCalc(bean);
            case 2:
                return new ICBCHalf36ProductCalc(bean);
            case 3:
                return new ICBCHalf12Quan36Fan4ProductCalc(bean);
            case 4:
                return new SelfFinancingLoanProduct(bean);
            case 5:
                return new ICBCHalf12Quan36Fan4ProductCalc(bean);
            case 6:
            case 7:
            case 8:
            default:
                return new ICBC36ProductCalc(bean);
        }
    }
}
