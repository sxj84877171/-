package com.soarsky.car.ui.home.fragment.personal;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.FamilyNumIlistBean;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.blindterm.BlindTermActivity;
import com.soarsky.car.ui.carchange.CarChangeActivity;
import com.soarsky.car.ui.family.familynum.FamilyNumActivity;
import com.soarsky.car.ui.family.familynumupdate.FamilyNumUpdateActivity;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.ui.modifypwd.ModifyPwdActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：个人中心<br>
 * 该类为 PersonalFragment<br>
 */
public class PersonalFragment extends BaseFragment<PersonalPresent,PersonalModel> implements PersonalView, View.OnClickListener{
    /**
     * 上下文本
     */
    public Context mContext;
    /**
     * 登陆或退出
     */
    private Button personalBackbtn;
    /**
     * 密码设置
     */
    private TextView personalWordTv;
    /**
     * 亲情号码
     */
    private TextView personalPhoneTv;
    /**
     * App绑定智能终端
     */
    private TextView app_blind_ter;
    /**
     * 切换车辆
     */
    private TextView app_change_car;
    /**
     * 用户名
     */
    private TextView fragmentPersonNameTv;
    /**
     * 需求建议
     */
    private TextView personaldemandTv;
    /**
     * 头像
     */
    private ImageView cover_user_photo;
    /**
     * 右边的
     */
    private ImageView rightImageView;
    /**
     * application
     */
    private App app;
    /**
     * 更换头像回调的标识
     */
    private static int RESULT_LOAD_IMAGE = 1;
    /**
     * 头像的路径
     */
    private String picturePath;


    public PersonalFragment() {
        // Required empty public constructor

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView(View view) {
        personalBackbtn = (Button) view.findViewById(R.id.personalBackbtn);
        personalBackbtn.setOnClickListener(this);
        personalWordTv = (TextView) view.findViewById(R.id.personalWordTv);
        personalWordTv.setOnClickListener(this);

        fragmentPersonNameTv = (TextView) view.findViewById(R.id.fragmentPersonNameTv);

        personalPhoneTv = (TextView) view.findViewById(R.id.personalPhoneTv);
        personalPhoneTv.setOnClickListener(this);

        app_blind_ter = (TextView) view.findViewById(R.id.app_blind_ter);
        app_blind_ter.setOnClickListener(this);

        app_change_car = (TextView) view.findViewById(R.id.app_change_car);
        app_change_car.setOnClickListener(this);

        personaldemandTv = (TextView)view.findViewById(R.id.personaldemandTv);
        personaldemandTv.setOnClickListener(this);

        cover_user_photo = (ImageView) view.findViewById(R.id.cover_user_photo);
        cover_user_photo.setOnClickListener(this);

        addView(R.layout.maintoolbarright);
        rightImageView = (ImageView) view.findViewById(R.id.rightImageView);
        rightImageView.setImageResource(R.mipmap.set_u);

        app = (App)mContext.getApplicationContext();

        if (app.isOnline() == true) {
            personalBackbtn.setText(getString(R.string.personalhomeback));
            fragmentPersonNameTv.setText("" + app.getCommonParam().getUserName());
        } else {
            personalBackbtn.setText(getString(R.string.personaltip));
            fragmentPersonNameTv.setText(getString(R.string.car_nologin));
        }


    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.personaltopic);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.personalBackbtn:
//          登陆或退出

            if (app.isOnline() == true){

                new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.back_title))
                        .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                app.setOnline(false);
                                app.exit();

                            }
                        })
                        .setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();


            }else{
                app.setOnline(false);
                Intent i = new Intent();
                i.setClass(mContext, LoginActivity.class);
                startActivity(i);
            }

                ToastUtil.show(mContext,"personalBackbtn");
                break;
            case R.id.personalWordTv:
//                密码设置
                if (app.isOnline() == false) {
                    ToastUtil.show(mContext, R.string.personaltip);
                    Intent i = new Intent();
                    i.setClass(mContext, LoginActivity.class);
                    startActivity(i);
                    return;
                }

                Intent in = new Intent();
                in.setClass(mContext, ModifyPwdActivity.class);
                startActivity(in);
                break;
            case R.id.personalPhoneTv:
//                亲情号码
                if (app.isOnline() == false) {
                    ToastUtil.show(mContext, R.string.personaltip);
                    Intent i = new Intent();
                    i.setClass(mContext, LoginActivity.class);
                    startActivity(i);
                    return;
                }

                mPresenter.deleteAll();

                break;
            case R.id.app_blind_ter:
                //app绑定
                if (app.isOnline() == false) {
                    ToastUtil.show(mContext, R.string.personaltip);
                    Intent i = new Intent();
                    i.setClass(mContext, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                Intent ii = new Intent();
                ii.setClass(mContext, BlindTermActivity.class);
                startActivity(ii);
                break;
            case R.id.app_change_car:
                //切换车辆
                if (app.isOnline() == false) {
                    ToastUtil.show(mContext, R.string.personaltip);
                    Intent i = new Intent();
                    i.setClass(mContext, LoginActivity.class);
                    startActivity(i);
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(mContext, CarChangeActivity.class);
                startActivity(intent);
                break;
            case R.id.personaldemandTv:
                // 需求建议
                startACTION_USAGE_ACCESS_SETTINGSPermissionActivity();

                break;
            case R.id.cover_user_photo:
                //修改头像
                if (app.isOnline() == false) {
                    ToastUtil.show(mContext, R.string.personaltip);
                    Intent i = new Intent();
                    i.setClass(mContext, LoginActivity.class);
                    startActivity(i);
                    return;
                }

                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

                break;
        }
    }

    private void startACTION_USAGE_ACCESS_SETTINGSPermissionActivity() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);    //通过尝试这个flag符合
        startActivity(intent);
    }

    /**
     * 获取亲情号码成功
     * @param param 返回参数
     */
    @Override
    public void showSuccess(ResponseDataBean<QueryFamilyBean> param) {


        if(param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){

                if(param.getData().getIlist().size()>0) {

                    //插入数据库
                    insertFamilyNum(param.getData().getIlist());
                    List<FamilyNumIlistBean> _list = new ArrayList<FamilyNumIlistBean>();
//                  剔除车主号码
                    for (FamilyNumIlistBean bean:param.getData().getIlist()){
                        if(bean.getIsOwner() == 0){
                            _list.add(bean);
                        }
                    }

                    if(_list.size()>0) {
                        Intent i = new Intent();
                        i.setClass(mContext, FamilyNumUpdateActivity.class);
                        startActivity(i);
                    }else {
                        Intent it = new Intent();
                        it.setClass(mContext, FamilyNumActivity.class);
                        startActivity(it);
                    }
                }else {
                    Intent it = new Intent();
                    it.setClass(mContext, FamilyNumActivity.class);
                    startActivity(it);
                }
            }else {
                ToastUtil.show(mContext,param.getMessage());
                Intent it = new Intent();
                it.setClass(mContext, FamilyNumActivity.class);
                startActivity(it);
            }
        }
    }

    /**
     * 获取亲情号码失败
     */
    @Override
    public void showFail() {
        ToastUtil.show(mContext,R.string.throwable);
    }

    /**
     * 删除亲情表成功
     */
    @Override
    public void deleteAllSuccess() {
        QueryFamilySendParam p = new QueryFamilySendParam();
        p.setPhone(app.getCommonParam().getOwerPhone());
        p.setUsername(app.getCommonParam().getUserName());

        p.setCarnum(app.getCommonParam().getCarNum());

        mPresenter.queryFirendPhone(p);
    }

    /**
     * 删除失败
     */
    @Override
    public void deleteAllFail() {

        ToastUtil.show(mContext,R.string.familynumupdatewrong);
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }

    /**
     * 更换图像方法
     * @param requestCode RESULT_LOAD_IMAGE
     * @param resultCode RESULT_OK
     * @param data 数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = mContext.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();



            cover_user_photo.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }

    }

    /**
     * 插入数据
     * @param list
     */
    private void insertFamilyNum(List<FamilyNumIlistBean> list){
        for (FamilyNumIlistBean bean:list){
            FamilyNum familyNum = new FamilyNum();
            familyNum.setPhone(bean.getPhone());
            familyNum.setUsername(app.getCommonParam().getUserName());
            familyNum.setCarnum(app.getCommonParam().getCarNum());
            familyNum.setIs_owner(bean.getIsOwner());
            familyNum.setSstate(1);
            familyNum.setTstate(0);
            mPresenter.insertFamilyNum(familyNum);
        }
    }


}
