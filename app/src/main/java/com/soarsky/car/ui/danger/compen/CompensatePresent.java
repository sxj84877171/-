package com.soarsky.car.ui.danger.compen;

import android.util.Log;
import android.view.View;

import com.soarsky.car.Constants;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CompenstateParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.SimpleImgBean;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.customview.ShowImgDialog;
import com.soarsky.car.uitl.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

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

    /**
     * 图片上传总数
     */
    private int upcount=0;

    private ResponseDataBean<List<SimpleImgBean>> mysimpleImg;


    @Override
    public void onStart() {
        mModel.setContext(context);
        getSimpleImg();
    }


    /**
     * 上传快赔信息
     * @param param CompenstateSendParam对象
     */
    public void uploadFastDamager(CompenstateSendParam param) {

        mView.showProgess();

        mModel.uploadFastDamager(param).subscribe(new Subscriber<ResponseDataBean<CompenstateParam>>() {
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
            public void onNext(ResponseDataBean<CompenstateParam> compenstateParam) {

                mView.stopProgess();
                mView.uploadFastDamagerSuccess(compenstateParam);
            }
        });

    }


    /**
     * 样照dialog
     */
    private ShowImgDialog showImgDialog;



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
     * @param index 类型
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
     * upStatus 上传状态 0 成功 1失败
     * @param index  类型
     * @param imgPath 图片路径
     */

    public void uploadImg(final int  index, String imgPath) {
//        mView.showProgess();
        upcount++;
        mModel.upLoadImg(imgPath).subscribe(new Subscriber<UploadFile>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                upcount--;
                mView.saveNetUri(index,"",false);
                LogUtil.i("onError" + e.getStackTrace().toString());
            }

            @Override
            public void onNext(UploadFile uploadFile) {
                upcount--;
                if (uploadFile.getCode().equals(Constants.REQUEST_SUCCESS)) {

                    mView.saveNetUri(index, uploadFile.getData().get(0).getFileUrl(),true);
                }else{
                    mView.saveNetUri(index,"",false);
                }

                LogUtil.i("onNext" + uploadFile.toJson());

            }
        });


    }



    public  void  upload(){



    }




    /**
     * 封装上传快照参数
     *
     * @param list1 现场远景
     * @param list2 现场近景
     * @param list3 事故损失全景
     * @param list4 受损部件特写
     * @param list5 事故车架
     * @param list6 双方驾照
     * @param list7 人车合影
     * @param id  基本信息生成的快赔信息id
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
     * 将图片路径集合拼接成上传用的字符串
     * @param list1 图片路径集合
     * @return String 字符串
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


    /**
     * 获取样照
     */
    public void getSimpleImg(){
        mModel.getSimpleImg().subscribe(new Subscriber<ResponseDataBean<List<SimpleImgBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean<List<SimpleImgBean>> simpleImg) {
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

//        if(upcount>0){
//            mView.upLoadImgIng();
//            return;
//        }

        mModel.upLoadFast(map).subscribe(new Subscriber<ResponseDataBean>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("onError");
            }

            @Override
            public void onNext(ResponseDataBean uploadFast) {
                LogUtil.i("onNext"+uploadFast.toJson());
                mView.stopProgess();
                if (uploadFast.getStatus().equals(Constants.REQUEST_SUCCESS)) {
                    mView.uploadFastImgSucess();
                } else {
                    mView.uploadFastImgf();
                }

            }
        });
    }












    /**
     * 验证图片是否完整
     * @param list1 现场远景
     * @param list2 现场近景
     * @param list3 事故损失全景
     * @param list4 受损部件特写
     * @param list5 事故车架
     * @param list6 双方驾照
     * @param list7 人车合影
     * @return TRUE or  false
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


    /**
     *  验证图片是否完整
     * @param list1 现场远景
     * @param list2 现场近景
     * @param list3 事故损失全景
     * @param list4 受损部件特写
     * @param list5 事故车架
     * @param list6 双方驾照
     * @param list7 人车合影
     * @return TRUE or  false
     */
    public boolean validateImageComplete2(List<CompensateImage> list1, List<CompensateImage> list2, List<CompensateImage> list3, List<CompensateImage> list4, List<CompensateImage> list5, List<CompensateImage> list6, List<CompensateImage> list7) {
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


    List<String> imageList1 = new ArrayList<>();
    List<String> imageList2 = new ArrayList<>();
    List<String> imageList3 = new ArrayList<>();
    List<String> imageList4 = new ArrayList<>();
    List<String> imageList5 = new ArrayList<>();
    List<String> imageList6 = new ArrayList<>();
    List<String> imageList7 = new ArrayList<>();

    public void uploadImage(final List<List<CompensateImage>> lists,final int id,final int localSize){
        mView.showProgess();

        clearData();
        for (List<CompensateImage> compensateImageList: lists){

            CompensateImage[] compensateImages = new CompensateImage[compensateImageList.size()];
            for(int i=0,j=compensateImageList.size();i<j;i++){
                compensateImages[i]=compensateImageList.get(i);
            }


            Observable.from(compensateImages).subscribe(new Action1<CompensateImage>() {
                @Override
                public void call(final CompensateImage compensateImage) {

                    mModel.upLoadImg(compensateImage.getImgUrl()).subscribe(new Subscriber<UploadFile>() {
                        @Override
                        public void onCompleted() {

                            int remoteSize = imageList1.size()+imageList2.size()+imageList3.size()+imageList4.size()+imageList5.size()+imageList6.size()
                                    +imageList7.size();
                            if(localSize == remoteSize){

                                upLoadParam(imageList1,imageList2,imageList3,imageList4,imageList5,imageList6,imageList7,id);



                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                            mView.stopProgess();
                            Log.d("TAG","onError");
                        }

                        @Override
                        public void onNext(UploadFile uploadFile) {
                            Log.d("TAG","upload=="+uploadFile.toJson());
                            switch (compensateImage.getFlag()){
                                case 1:
                                    imageList1.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                                case 2:
                                    imageList2.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                                case 3:
                                    imageList3.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                                case 4:
                                    imageList4.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                                case 5:
                                    imageList5.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                                case 6:
                                    imageList6.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                                case 7:
                                    imageList7.add(uploadFile.getData().get(0).getFileUrl());
                                    break;
                            }
                        }
                    });

                }
            });

        }

    }


    public void clearData(){
        imageList1.clear();
        imageList2.clear();
        imageList3.clear();
        imageList4.clear();
        imageList5.clear();
        imageList6.clear();
        imageList7.clear();
    }

}
