package com.soarsky.policeclient.ui.record.fragment;

import com.soarsky.policeclient.data.local.db.bean.Violation;

import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 历史记录--电子罚单视图层<br>
 */

public interface IElectronicFragmentView {
    /**
     * 参考其他View
     * @param list
     */
    public void showData(List<Violation> list);

}
