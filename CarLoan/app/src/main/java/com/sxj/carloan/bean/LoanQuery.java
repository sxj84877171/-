package com.sxj.carloan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LoanQuery implements Parcelable {
    public static final Creator<LoanQuery> CREATOR = new Creator<LoanQuery>() {
        @Override
        public LoanQuery createFromParcel(Parcel source) {
            LoanQuery var = new LoanQuery();
            var.ds_num = source.readInt();
            var.fk_num = source.readInt();
            var.dcy_num = source.readInt();
            var.loan_cw_num = source.readInt();
            var.ywy_case_num = source.readInt();
            var.dispatch_num = source.readInt();
            var.zhengxin_num = source.readInt();
            var.baoxian_num = source.readInt();
            var.ywy_qk_num = source.readInt();
            return var;
        }

        @Override
        public LoanQuery[] newArray(int size) {
            return new LoanQuery[size];
        }
    };
    private int ds_num;
    private int fk_num;
    private int dcy_num;
    private int loan_cw_num;
    private int ywy_case_num;
    private int dispatch_num;
    private int zhengxin_num;
    private int baoxian_num;
    private int ywy_qk_num;

    public int getDs_num() {
        return this.ds_num;
    }

    public void setDs_num(int ds_num) {
        this.ds_num = ds_num;
    }

    public int getFk_num() {
        return this.fk_num;
    }

    public void setFk_num(int fk_num) {
        this.fk_num = fk_num;
    }

    public int getDcy_num() {
        return this.dcy_num;
    }

    public void setDcy_num(int dcy_num) {
        this.dcy_num = dcy_num;
    }

    public int getLoan_cw_num() {
        return this.loan_cw_num;
    }

    public void setLoan_cw_num(int loan_cw_num) {
        this.loan_cw_num = loan_cw_num;
    }

    public int getYwy_case_num() {
        return this.ywy_case_num;
    }

    public void setYwy_case_num(int ywy_case_num) {
        this.ywy_case_num = ywy_case_num;
    }

    public int getDispatch_num() {
        return this.dispatch_num;
    }

    public void setDispatch_num(int dispatch_num) {
        this.dispatch_num = dispatch_num;
    }

    public int getZhengxin_num() {
        return this.zhengxin_num;
    }

    public void setZhengxin_num(int zhengxin_num) {
        this.zhengxin_num = zhengxin_num;
    }

    public int getBaoxian_num() {
        return this.baoxian_num;
    }

    public void setBaoxian_num(int baoxian_num) {
        this.baoxian_num = baoxian_num;
    }

    public int getYwy_qk_num() {
        return this.ywy_qk_num;
    }

    public void setYwy_qk_num(int ywy_qk_num) {
        this.ywy_qk_num = ywy_qk_num;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ds_num);
        dest.writeInt(this.fk_num);
        dest.writeInt(this.dcy_num);
        dest.writeInt(this.loan_cw_num);
        dest.writeInt(this.ywy_case_num);
        dest.writeInt(this.dispatch_num);
        dest.writeInt(this.zhengxin_num);
        dest.writeInt(this.baoxian_num);
        dest.writeInt(this.ywy_qk_num);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
