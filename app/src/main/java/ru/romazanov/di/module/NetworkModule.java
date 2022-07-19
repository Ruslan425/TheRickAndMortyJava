package ru.romazanov.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.romazanov.data.retrofit.RetrofitApiInterface;

@Module
public class NetworkModule {

    private String baseUrl = "https://rickandmortyapi.com/api/";

    @Provides
    @Singleton
    public RetrofitApiInterface getRetrofitApiInterface(Retrofit retrofit) {
        return retrofit.create(RetrofitApiInterface.class);
    }


    @Provides
    @Singleton
    public Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
