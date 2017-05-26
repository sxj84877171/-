package com.soarsky.car.ui.danger.compen;

import android.view.View;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.SimpleImg;
import com.soarsky.car.bean.UploadFast;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.customview.ShowImgDialog;
import com.soarsky.car.uitl.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CompensatePresent extends BasePresenter<CompensateModel, CompensateView> {

    private SimpleImg mysimpleImg;


    @Override
    public void onStart() {
        mModel.setContext(context);
        getSimpleImg();
    }


    public View getImageView(String imgUrl) {
        return mModel.getImageView(imgUrl, null);
    }

    /**
     * 上传快赔信息
     */
    public void uploadFastDamager(CompenstateSendParam param) {

        mView.showProgess();

        mModel.uploadFastDamager(param).subscribe(new Subscriber<CompenstateParam>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.uploadFastDamagerFail();
            }

            @Override
            public void onNext(CompenstateParam compenstateParam) {

                mView.stopProgess();
                mView.uploadFastDamagerSuccess(compenstateParam);
            }
        });

    }

    public boolean verifyData() {
        return mView.verifyData();
    }


    private ShowImgDialog showImgDialog;

    /**
     * 初始化图片数据
     *
     * @param data
     * @return
     */
    public List<CompensateImage> initData(List<CompensateImage> data) {
        CompensateImage compensateImage = new CompensateImage();
        compensateImage.setImgUrl("");
        compensateImage.setShowDelete(0);
        data.add(compensateImage);
        return data;
    }


    /**
     * 添加图片数据
     *
     * @param data
     * @return
     */
    public List<CompensateImage> addData(List<CompensateImage> data) {
        CompensateImage compensateImage = new CompensateImage();
        compensateImage.setImgUrl("");
        compensateImage.setShowDelete(1);
        data.add(compensateImage);
        return data;
    }

    /**
     * 显示样照
     */
    public void showImg(int index ) {
        if (showImgDialog == null) {
            showImgDialog = new ShowImgDialog(context);
        }
        try {
            showImgDialog.setImgUrl(mysimpleImg.getData().get(index).getUrl());
        }catch (Exception e){
            
        }

        showImgDialog.show();
    }


    /**
     * 照片上传
     */

    public void uploadImg(final List<String> list, String imgPath) {
        mModel.upLoadImg(imgPath).subscribe(new Subscriber<UploadFile>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("onError" + e.getStackTrace().toString());
            }

            @Override
            public void onNext(UploadFile uploadFile) {
                if (uploadFile.getCode().equals("0")) {
                    mView.saveNetUri(list, uploadFile.getData().get(0).getFileUrl());
                }

                LogUtil.i("onNext" + uploadFile.toJson());

            }
        });


    }

    /**
     * 封装上传快照参数
     *
     * @param list1
     * @param list2
     * @param list3
     * @param list4
     * @param list5
     * @param list6
     * @param list7
     * @param id
     */
    public void upLoadParam(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, List<String> list7, int id) {
        HashMap<String, String> param = new HashMap<>();
        param.put("imageUrl1", createImgParam(list1));
        param.put("imageUrl2", createImgParam(list2));
        param.put("imageUrl3", createImgParam(list3));
        param.put("imageUrl4", createImgParam(list4));
        param.put("imageUrl5", createImgParam(list5));
        param.put("imageUrl6", createImgParam(list6));
        param.put("imageUrl7", createImgParam(list7));
        param.put("anumber", id + "");
        upLoadFast(param);


    }


    /**
     * @param list1
     * @return
     */
    private String createImgParam(List<String> list1) {
        StringBuilder sb = new StringBuilder();

        for (String string : list1
                ) {
            sb.append(string);
            sb.append(",");
        }
        String str = sb.toString();
        if (str.length() > 0) {
            return str.substring(0, str.length() - 1);
        }
        return "";
    }


    public void getSimpleImg(){
        mModel.getSimpleImg().subscribe(new Subscriber<SimpleImg>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SimpleImg simpleImg) {
                mysimpleImg=simpleImg;
            }
        });

    }



    /**
     * 上传快照信息
     *
     * @param map
     */
    private void upLoadFast(Map<String, String> map) {
        mModel.upLoadFast(map).subscribe(new Subscriber<UploadFast>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("onError");
            }

            @Override
            public void onNext(UploadFast uploadFast) {
                LogUtil.i("onNext");
                if (uploadFast.getStatus().equals("0")) {
                    mView.uploadFastImgSucess();
                } else {
                    mView.uploadFastImgf();
                }

            }
        });
    }


    /**
     * 验证图片是否完整
     */


    public boolean validateImageComplete(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, List<String> list7) {
        if (list1.size() == 0) {
            return false;
        } else if (list2.size() == 0) {
            return false;
        } else if (list3.size() == 0) {
            return false;
        } else if (list4.size() == 0) {
            return false;
        } else if (list5.size() == 0) {
            return false;
        } else if (list6.size() == 0) {
            return false;
        } else if (list7.size() == 0) {
            return false;
        }

        return true;
    }

}
