package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/22.
 */

public abstract class ProductType implements IProductType {

    private double loan_amount_ywy;

    public ProductType(double loan_amount_ywy){
        this.loan_amount_ywy = loan_amount_ywy;
    }

    public double getLoan_amount_ywy() {
        return loan_amount_ywy;
    }

    public double roundDouble1(double d) {
        if (((long) d * 10) % 10 == 0) {
            d += 0.1;
        }
        d = ((long) (d * 10)) / 10.0;
        return d;
    }

    public double roundDouble(double d) {
        if (((long) (d * 10)) % 10 != 0) {
            d += 1;
        }
        d = ((long) d);
        return d;
    }

    /**
     * 调整项
     *
     * @return
     */
    @Override
    public double getExtrasFee() {
        return extrasFee;
    }

    private double extrasFee;

    /**
     * 调整项
     *
     * @param extrasFee
     */
    @Override
    public void setExtrasFee(double extrasFee) {
        this.extrasFee = extrasFee;
    }
}
