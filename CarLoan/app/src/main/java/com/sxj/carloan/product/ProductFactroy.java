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
            case 2:
                return new ICBCUsedCarHalf36M0188B(ywy);
            case 3:
                return new ICBCUsedCar12Whole36M2448B(ywy);
            case 4:
                return new SelfFinancingLoanProduct(ywy);
            case 5:
                return new ICBCUsedCar12Whole36M2448BPart(ywy);
            case 6:
                return new ICBCNewCarWhole36M18F3(ywy);
            case 7:
                return new ICBCNewCarHalf36M1350(ywy);
            case 8:
                return new ICBCNewCarHalf24M1050(ywy);
            default:
                return new ICBCUsedCarWhole36M2448B(ywy);
        }
    }
}
