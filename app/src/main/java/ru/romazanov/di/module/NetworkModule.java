package ru.romazanov.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.romazanov.data.retrofit.MyInterceptor;
import ru.romazanov.data.retrofit.RetrofitApiInterface;
import ru.romazanov.data.room.DataBase;
import ru.romazanov.data.room.dao.MyInterceptorDao;

@Module
public class NetworkModule {

    private String baseUrl = "https://rickandmortyapi.com/api/";

    @Singleton
    @Provides
    public OkHttpClient getOkHttpClient(MyInterceptorDao dao) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor(dao))
                .build();
        return okHttpClient;
    };

    @Provides
    @Singleton
    public RetrofitApiInterface getRetrofitApiInterface(Retrofit retrofit) {
        return retrofit.create(RetrofitApiInterface.class);
    }

    @Provides
    @Singleton
    public Retrofit getRetrofitInstance(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


}
