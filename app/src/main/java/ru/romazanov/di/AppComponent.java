package ru.romazanov.di;



import dagger.Component;
import ru.romazanov.di.module.AppModule;
import ru.romazanov.therickandmortyjava.MainActivity;

@Component(modules = {
        AppModule.class,
})
public interface AppComponent {

    void inject(MainActivity mainActivity);

}
