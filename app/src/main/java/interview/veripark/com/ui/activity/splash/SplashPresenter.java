package interview.veripark.com.ui.activity.splash;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.ui.base.BasePresenter;
import interview.veripark.com.utils.DeviceAndSystemInfoUtils;
import interview.veripark.com.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager mDataManager, SchedulerProvider mSchedulerProvider, CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onHandShakeStart(HandShakeRequest shakeRequest) {

        getCompositeDisposable().add(getDataManager()
                .doHandShakeStartApiCall(shakeRequest.toJSONString())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    System.out.println(response.toString());

                    getDataManager().updateApiHeader(
                            response.getAesKey(),
                            response.getAesIV(),
                            response.getAuthorization()
                    );

                  /* getDataManager().updateUserInfo(
                            response.getAccessToken(),
                            response.getUserId(),
                            DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                            response.getUserName(),
                            response.getUserEmail(),
                            response.getGoogleProfilePicUrl());*/

                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().openMainActivity();

                }, throwable -> {

                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the login error here
                    //  if (throwable instanceof ANError) {
                    //   ANError anError = (ANError) throwable;
                    //   handleApiError(anError);
                    // }
                }));


    }
}
