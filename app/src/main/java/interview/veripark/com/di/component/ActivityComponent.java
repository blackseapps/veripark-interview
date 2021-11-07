package interview.veripark.com.di.component;

import dagger.Component;
import interview.veripark.com.di.PerActivity;
import interview.veripark.com.di.module.ActivityModule;
import interview.veripark.com.ui.activity.splash.SplashActivity;
import interview.veripark.com.ui.main.MainActivity;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SplashActivity splashActivity);
}
