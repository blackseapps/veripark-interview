package interview.veripark.com.di.module;

import android.content.Context;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.veripark.com.R;
import interview.veripark.com.data.network.ApiHeader;
import interview.veripark.com.di.AesKey;
import interview.veripark.com.di.AesVI;
import interview.veripark.com.di.ApiInfo;
import interview.veripark.com.di.ApplicationContext;
import interview.veripark.com.di.PerActivity;
import interview.veripark.com.di.ProtectedHeader;
import interview.veripark.com.ui.activity.detail.DetailMvpPresenter;
import interview.veripark.com.ui.activity.detail.DetailMvpView;
import interview.veripark.com.ui.activity.detail.DetailPresenter;
import interview.veripark.com.ui.activity.splash.SplashMvpPresenter;
import interview.veripark.com.ui.activity.splash.SplashMvpView;
import interview.veripark.com.ui.activity.splash.SplashPresenter;
import interview.veripark.com.ui.fragment.stock.StockAdapter;
import interview.veripark.com.ui.fragment.stock.StockAndIndexMvpPresenter;
import interview.veripark.com.ui.fragment.stock.StockAndIndexMvpView;
import interview.veripark.com.ui.fragment.stock.StockAndIndexPresenter;
import interview.veripark.com.ui.main.MainMvpPresenter;
import interview.veripark.com.ui.main.MainMvpView;
import interview.veripark.com.ui.main.MainPresenter;
import interview.veripark.com.utils.rx.AppSchedulerProvider;
import interview.veripark.com.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

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
    @ApplicationContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
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

    @Provides
    @PerActivity
    StockAndIndexMvpPresenter<StockAndIndexMvpView> provideStockAndIndexPresenter(
            StockAndIndexPresenter<StockAndIndexMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailMvpView> provideDetailPresenter(
            DetailPresenter<DetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    StockAdapter provideAddingAdapter() {
        return new StockAdapter(mActivity, new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }




}