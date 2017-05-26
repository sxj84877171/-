package com.soarsky.car.data.remote.server1;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.uitl.HostUtil;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
public class ApiF {

    private Retrofit retrofit;
    private ApiService service;
    private String url;

    Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            LogUtil.i("url=="+chain.request().url());
            Log.d("TAG","url=="+chain.request().url());
            return chain.proceed(chain.request().newBuilder()
//                    .addHeader("Token", "Token")
                    .addHeader("Content-Type", "application/octet-stream")
//                    .addHeader("Content-Type", "application/x-jpg")
                    .build());
        }
    };

    private ApiF() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        File cacheFile = new File(App.getApp().getCacheDir(), "cache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(90000, TimeUnit.MILLISECONDS)
                .connectTimeout(90000, TimeUnit.MILLISECONDS)
                .writeTimeout(90000,TimeUnit.MILLISECONDS)
                .addInterceptor(mInterceptor)
//                .addInterceptor(interceptor)
                .addNetworkInterceptor(new HttpCacheInterceptor())
//                .cache(cache)
                .build();


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        //initURL();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL2)
                .build();
        service = retrofit.create(ApiService.class);
    }


    private void initURL(){
        url = HostUtil.getHostUrl2();
        if(url == null){
            url = Constants.BASE_URL2 ;
        }
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final ApiF INSTANCE = new ApiF();
    }

    //获取单例
    public static ApiF getInstance() {
        return SingletonHolder.INSTANCE;
    }


    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(App.getApp())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(App.getApp())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }


    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    public ApiService getService() {
        return service;
    }
}
