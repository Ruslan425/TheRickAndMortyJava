package ru.romazanov;

import android.app.Application;

import ru.romazanov.di.AppComponent;
import ru.romazanov.di.DaggerAppComponent;
import ru.romazanov.di.module.AppModule;
import ru.romazanov.di.module.RoomModule;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
