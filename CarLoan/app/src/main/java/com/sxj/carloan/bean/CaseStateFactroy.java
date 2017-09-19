package com.sxj.carloan.bean;

/**
 * Created by admin on 2017/8/22.
 */

public class CaseStateFactroy {

    private CaseStateFactroy() {
    }

    private static CaseStateFactroy instance = new CaseStateFactroy();

    public static CaseStateFactroy getInstance() {
        return instance;
    }

    /**
     * [{ id: 0, text: '建档中' }, { id: 1, text: '征信待查' }, { id: 2, text: '保险待查' },
     * { id: 3, text: '待传照片' }, { id: 4, text: '待派单' },    { id: 5, text: '待调查' },
     * { id: 6, text: '待初审' }, { id: 7, text: '待终审' }, { id: 8, text: '待请款' },
     * { id: 9, text: '待税费汇总' },    { id: 10, text: '待电审' }, { id: 11, text: '待税费确认' },
     * { id: 12, text: '待批复' }, { id: 13, text: '待放款' }, { id: 14, text: '待住行审核' },
     * { id: 15, text: '待住行送审' }, { id: 16, text: '待调额' }, { id: 17, text: '待刷卡' },
     * { id: 18, text: '已刷卡' }, { id: 19, text: '待复审' }, { id: 20, text: '已放款' },
     * { id: 101, text: '征信退回' }, { id: 105, text: '调查退回' }, { id: 106, text: '风控退回' },
     * { id: 107, text: '审批退回' }, { id: 110, text: '电审退回' }, { id: 112, text: '批复退回' },
     * { id: 114, text: '驻行审核退' }, { id: 115, text: '驻行送审退' } ];
     *
     * @param id
     * @return
     */
    public String getCaseState(int id) {
        switch (id) {
//            [{ id: 0, text: '建档中' }, { id: 1, text: '征信待查' }, { id: 2, text: '保险待查' },
            case 0:
                return "建档中";
            case 1:
                return "征信待查";
            case 2:
                return "保险待查";
//            { id: 3, text: '待传照片' }, { id: 4, text: '待派单' },    { id: 5, text: '待调查' },
            case 3:
                return "待传照片";
            case 4:
                return "待派单";
            case 5:
                return "待调查";
//            { id: 6, text: '待初审' }, { id: 7, text: '待终审' }, { id: 8, text: '待请款' },
            case 6:
                return "待初审";
            case 7:
                return "待终审";
            case 8:
                return "待请款";
//            { id: 9, text: '待税费汇总' },    { id: 10, text: '待电审' }, { id: 11, text: '待税费确认' }
            case 9:
                return "待税费汇总";
            case 10:
                return "待电审";
            case 11:
                return "待税费确认";
//            { id: 12, text: '待批复' }, { id: 13, text: '待放款' }, { id: 14, text: '待住行审核' },
            case 12:
                return "待批复";
            case 13:
                return "待放款";
            case 14:
                return "待住行审核";
//            { id: 15, text: '待住行送审' }, { id: 16, text: '待调额' }, { id: 17, text: '待刷卡' },
            case 15:
                return "待调额";
            case 16:
                return "待住行审核";
            case 17:
                return "待刷卡";
//     * { id: 18, text: '已刷卡' }, { id: 19, text: '待复审' }, { id: 20, text: '已放款' },

            case 18:
                return "已刷卡";
            case 19:
                return "待复审";
            case 20:
                return "已放款";

//     * { id: 101, text: '征信退回' }, { id: 105, text: '调查退回' }, { id: 106, text: '风控退回' },
            case 101:
                return "征信退回";
            case 105:
                return "调查退回";
            case 106:
                return "风控退回";
//     * { id: 107, text: '审批退回' }, { id: 110, text: '电审退回' }, { id: 112, text: '批复退回' },
            case 107:
                return "审批退回";
            case 110:
                return "电审退回";
            case 112:
                return "批复退回";
//     * { id: 114, text: '驻行审核退' }, { id: 115, text: '驻行送审退' } ];
            case 114:
                return "驻行审核退";
            case 115:
                return "驻行送审退";
            case 199:
                return "拒绝贷款";
            default:
                return "未知";

        }
    }
}
