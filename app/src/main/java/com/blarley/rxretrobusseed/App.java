package com.blarley.rxretrobusseed;

import android.app.Application;

import com.blarley.rxretrobusseed.annotationprocessor.generated.Clients;
import com.blarley.rxretrobusseed.library.bus.RxRetroBus;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class App extends Application {
    private OkHttpClient okHttpClient;
    private Retrofit.Builder retrofitBuilder;

    public static Clients clients;
    public static RxRetroBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        okHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        retrofitBuilder = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient);
        bus = new RxRetroBus();
        clients = new Clients(retrofitBuilder, bus);
    }
}
