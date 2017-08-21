package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/21.
 */

public class ProductFactroy {

    private static ProductFactroy instance = new ProductFactroy();

    private ProductFactroy() {
    }

    public static ProductFactroy getInstance() {
        return instance;
    }

    public IProductType processProductType(int productId, double ywy) {
        switch (productId) {
            case 1:
                return new ICBCUsedCarWhole36M2448B(ywy);
            default:
                return new ICBCUsedCarWhole36M2448B(ywy);
        }
    }
}
