package com.sxj.bank.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sxj.bank.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 车主APP<br>
 * 作者： 孙向锦<br>
 * 时间： 8/18/2016<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 请求后台服务器封装，对后台所有请求的管理<br>
 */
public class Api {

    public Retrofit retrofit;
    public ApiService service;

    public ApiService getService() {
        return service;
    }

    public void setService(ApiService service) {
        this.service = service;
    }

    private String url;

    /**
     * 拦截器1
     * 如果后台服务器需要用到token，则每次请求自动对token的添加验证
     * 自动添加请求数据的格式，所有请求URL连接输出
     */
    Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String url = chain.request().url().toString();
            LogUtil.i("url==" + url);
            Response response = chain.proceed(chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build());
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            LogUtil.i(url + " : " + buffer.clone().toString());
            return response;
        }


    };

    private Api() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        //initURL();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://carmis.timesly.cn/")
                .build();
        service = retrofit.create(ApiService.class);


    }


    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    /**
     * 获取单例
     */
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }


}



