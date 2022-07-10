package ru.romazanov;

import android.app.Application;

import ru.romazanov.di.AppComponent;
import ru.romazanov.di.DaggerAppComponent;
import ru.romazanov.di.module.AppModule;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
