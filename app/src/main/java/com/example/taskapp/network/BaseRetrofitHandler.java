package com.example.taskapp.network;

import com.example.taskapp.MyApplication;
import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofitHandler<T> {
    private static final String TAG = "BaseRetrofitHandler";
    private static final String BASE_URL = "http://hn.algolia.com/";
    public ApiService apiService;
    private static BaseRetrofitHandler instance=null;
    private static Retrofit retrofit;

    public BaseRetrofitHandler() {
    }
    public static BaseRetrofitHandler getInstance(){
        if (instance==null){
            synchronized (BaseRetrofitHandler.class){
                instance=new BaseRetrofitHandler();
            }
        }
        return instance;
    }
    public void setupRetrofitAndOkHttp(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        File httpcacheDirectory=new File(MyApplication.getContext().getCacheDir(),"Offline");
        Cache cache=new Cache(httpcacheDirectory,10*1024*1024);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(provideCacheInterceptor())
                .addInterceptor(provideOfflineCacheInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .baseUrl(BASE_URL)

                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private Interceptor provideCacheInterceptor() {

        return chain -> {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);
            String cacheControl = originalResponse.header("Cache-Control");

            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-stale=0")) {


                CacheControl cc = new CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build();

                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-stale=" + 60  *60 * 24)
                        .build();

            } else {
                return originalResponse;
            }
        };

    }


    private Interceptor provideOfflineCacheInterceptor() {

        return chain -> {
            try {
                return chain.proceed(chain.request());
            } catch (Exception e) {


                CacheControl cacheControl = new CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(1, TimeUnit.DAYS)
                        .build();

                Request offlineRequest = chain.request().newBuilder()
                        .cacheControl(cacheControl)
                        .build();
                return chain.proceed(offlineRequest);
            }
        };
    }
    }


