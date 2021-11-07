package interview.veripark.com;

import android.app.Application;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.di.component.ApplicationComponent;
import interview.veripark.com.di.component.DaggerApplicationComponent;
import interview.veripark.com.di.module.ApplicationModule;

/**
 * Created by mertKaradeniz on 5.11.2021
 * <p>
 * This is an interview project.
 */

public class MainApplication extends Application {


    @Inject
    DataManager manager;

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
