package interview.veripark.com.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import interview.veripark.com.di.PerActivity;
import interview.veripark.com.ui.activity.splash.SplashMvpPresenter;
import interview.veripark.com.ui.activity.splash.SplashMvpView;
import interview.veripark.com.ui.activity.splash.SplashPresenter;
import interview.veripark.com.ui.main.MainMvpPresenter;
import interview.veripark.com.ui.main.MainMvpView;
import interview.veripark.com.ui.main.MainPresenter;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }
}