package com.soarsky.policeclient.ui.accident.listitem;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentItem;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.TimeUtils;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析每一个事故详情页面Present<br>
 */
public class AccidentListItemPresent extends BasePresenter<AccidentListItemModel,AccidentListItemView> {
    @Override
    public void onStart() {

    }

    /**
     * 根据id从服务器查询对应的事故并解析
     * @param id 从上个界面事故列表传过来的事故的id
     */
    public void getDataFromServer(String id){
        mModel.getAccidentListItem(id).subscribe(new Subscriber<ResponseDataBean<AccidentItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(ResponseDataBean<AccidentItem> accidentItem) {

                if(accidentItem!=null && accidentItem.getData()!=null){
                    AccidentDataBean.AccidentItemBean accidentItemBean = new AccidentDataBean.AccidentItemBean();
                    accidentItemBean.setTime(accidentItem.getData().getAtime());
                    accidentItemBean.setBeizhu(accidentItem.getData().getRemark());
                    accidentItemBean.setWeizhi(accidentItem.getData().getLocation());
                    accidentItemBean.setYuanyin(accidentItem.getData().getReason());
                    ArrayList<AccidentDataBean.AccidentItemBean.Image> imageArrayList = new ArrayList<AccidentDataBean.AccidentItemBean.Image>();
                    if(accidentItem.getData().getImageUrl()!=null && !accidentItem.getData().getImageUrl().equals("")){
                        String[] images = accidentItem.getData().getImageUrl().split(",");
                        for (String imageStr: images) {
                            AccidentDataBean.AccidentItemBean.Image image = new AccidentDataBean.AccidentItemBean.Image();
                            image.setServerPath(imageStr);
                            imageArrayList.add(image);
                        }
                    }
                    accidentItemBean.setImages(imageArrayList);
                    ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean> accidentCarBeenList = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean>();
                    if(accidentItem.getData().getArlist()!=null && accidentItem.getData().getArlist().size()!=0){
                        for (AccidentItem.Arlist arlist:accidentItem.getData().getArlist()) {
                            AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean = new AccidentDataBean.AccidentItemBean.AccidentCarBean();
                            accidentCarBean.setId(arlist.getId());
                            accidentCarBean.setAnquandai(CarUtil.parseCarAnquandai(arlist.getSafeState()));
                            accidentCarBean.setType(arlist.getCtype());
                            Car car = new Car();
                            car.setCarNum(arlist.getAcarnum());
                            if(!("2".equals(arlist.getSafeState()))){
                                car.setHasData(true);
                            }
                            accidentCarBean.setCar(car);
                            ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem> dengItems = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem>();
                            if(arlist.getL1()!=null && arlist.getLtime1()!=null && !arlist.getL1().equals("") && !arlist.getLtime1().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem dengItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem();
                                dengItem.setTime(arlist.getLtime1());
                                dengItem.setDeng(arlist.getL1());
                                dengItems.add(dengItem);
                            }
                            if(arlist.getL2()!=null && arlist.getLtime2()!=null && !arlist.getL2().equals("") && !arlist.getLtime2().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem dengItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem();
                                dengItem.setTime(arlist.getLtime2());
                                dengItem.setDeng(arlist.getL2());
                                dengItems.add(dengItem);
                            }
                            if(arlist.getL3()!=null && arlist.getLtime3()!=null && !arlist.getL3().equals("") && !arlist.getLtime3().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem dengItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem();
                                dengItem.setTime(arlist.getLtime3());
                                dengItem.setDeng(arlist.getL3());
                                dengItems.add(dengItem);
                            }
                            if(arlist.getL4()!=null && arlist.getLtime4()!=null && !arlist.getL4().equals("") && !arlist.getLtime4().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem dengItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem();
                                dengItem.setTime(arlist.getLtime4());
                                dengItem.setDeng(arlist.getL4());
                                dengItems.add(dengItem);
                            }
                            if(arlist.getL5()!=null && arlist.getLtime5()!=null && !arlist.getL5().equals("") && !arlist.getLtime5().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem dengItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem();
                                dengItem.setTime(arlist.getLtime5());
                                dengItem.setDeng(arlist.getL5());
                                dengItems.add(dengItem);
                            }
                            accidentCarBean.setDengItems(dengItems);
                            ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu> sudus = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu>();
                            if(arlist.getS1()!=null && arlist.getStime1()!=null && !arlist.getS1().equals("") && !arlist.getStime1().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu sudu = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu();
                                String[] suduStrs = arlist.getS1().split(",");
                                ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem> suduItems = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem>();
                                for (int i=0;i<suduStrs.length;i++) {
                                    AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem suduItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem();
                                    suduItem.setValue(suduStrs[i]);
                                    suduItem.setTime(TimeUtils.date2String(TimeUtils.addSecond(TimeUtils.string2Date(arlist.getStime1()),i)));
                                    suduItems.add(suduItem);
                                }
                                sudu.setSuduItems(suduItems);
                                sudus.add(sudu);

                            }
                            if(arlist.getS2()!=null && arlist.getStime2()!=null && !arlist.getS2().equals("") && !arlist.getStime2().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu sudu = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu();
                                String[] suduStrs = arlist.getS2().split(",");
                                ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem> suduItems = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem>();
                                for (int i=0;i<suduStrs.length;i++) {
                                    AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem suduItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem();
                                    suduItem.setValue(suduStrs[i]);
                                    suduItem.setTime(TimeUtils.date2String(TimeUtils.addSecond(TimeUtils.string2Date(arlist.getStime2()),i)));
                                    suduItems.add(suduItem);
                                }
                                sudu.setSuduItems(suduItems);
                                sudus.add(sudu);

                            }
                            if(arlist.getS3()!=null && arlist.getStime3()!=null && !arlist.getS3().equals("") && !arlist.getStime3().equals("")){
                                AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu sudu = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu();
                                String[] suduStrs = arlist.getS3().split(",");
                                ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem> suduItems = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem>();
                                for (int i=0;i<suduStrs.length;i++) {
                                    AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem suduItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem();
                                    suduItem.setValue(suduStrs[i]);
                                    suduItem.setTime(TimeUtils.date2String(TimeUtils.addSecond(TimeUtils.string2Date(arlist.getStime3()),i)));
                                    suduItems.add(suduItem);
                                }
                                sudu.setSuduItems(suduItems);
                                sudus.add(sudu);

                            }
                            accidentCarBean.setSudus(sudus);
                            ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem> guzhangItems = new ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem>();
                            if(arlist.getFlist()!=null && arlist.getFlist().size()!=0){
                                for (AccidentItem.Flist flist:arlist.getFlist()) {
                                    AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem guzhangItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem();
                                    guzhangItem.setId(flist.getId());
                                    guzhangItem.setGuzhang(flist.getRemark());
                                    guzhangItem.setTime(flist.getAtime());
                                    guzhangItems.add(guzhangItem);
                                }
                            }
                            accidentCarBean.setGuzhangItems(guzhangItems);
                            accidentCarBeenList.add(accidentCarBean);
                        }
                    }
                    accidentItemBean.setAccidentCarBeanList(accidentCarBeenList);
                    mView.showData(accidentItemBean);
                }else {
                    mView.onNoData();
                }


            }
        });
    }

}
