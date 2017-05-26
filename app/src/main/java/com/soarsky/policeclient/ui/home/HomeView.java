package com.soarsky.policeclient.ui.home;

import com.soarsky.policeclient.base.BaseView;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.Note;
import com.soarsky.policeclient.ui.violation.UploadFile;

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
 * 该类为  首页界面<br>
 */
public interface HomeView extends BaseView {
    /**
     * 参考其他的view
     * @param notes
     */
    public void showResult(List<Note> notes);
    /**
     * 参考其他的view
     */
    public void showSuccess();
    /**
     * 参考其他的view
     */
    public void showFail();
    /**
     * 图片上传成功
     * @param uploadFile
     */
    public void uploadImgSuccess(UploadFile uploadFile);

    /**
     * 图片上传失败
     */
    public void uploadImgFail();

    /**
     * 上传图片信息成功
     * @param stringResponseDataBean
     */
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean);

    /**
     * 上传图片信息失败
     */
    public void modifyAppImageFail();
}
