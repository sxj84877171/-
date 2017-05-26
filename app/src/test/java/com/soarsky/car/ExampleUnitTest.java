package com.soarsky.car;

import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.ui.forget.ForgetParam;
import com.soarsky.car.ui.forget.ForgetPwdPresent;
import com.soarsky.car.ui.login.LoginParam;
import com.soarsky.car.ui.modifypwd.ModifyPwdParam;

import org.junit.Test;

import rx.Subscriber;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Api.getInstance().service.login("zse","123456").subscribe(new Subscriber<LoginParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.print(e);

            }

            @Override
            public void onNext(LoginParam loginParam) {
                System.out.print(loginParam.getMessage());
            }
        });
    }

    @Test
    public  void textForgetPwd(){

        Api.getInstance().service.forgetPassword("zss","42012322222222222","13888888888").subscribe(new Subscriber<ForgetParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.print(e);

            }

            @Override
            public void onNext(ForgetParam forgetParam) {
                System.out.print(forgetParam.getMessage());
            }
        });

    }

    @Test
    public void testModPwd(){
        new ModifyPwdModel().modifyPwd("zss","111","123456").subscribe(new Subscriber<ModifyPwdParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.print(e);
            }

            @Override
            public void onNext(ModifyPwdParam modifyPwdParam) {
                System.out.print(modifyPwdParam.getMessage());
            }
        });



    }


}