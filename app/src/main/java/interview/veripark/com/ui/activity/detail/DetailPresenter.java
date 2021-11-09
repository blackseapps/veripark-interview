package interview.veripark.com.ui.activity.detail;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.data.network.model.DetailRequest;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.ui.activity.splash.SplashMvpPresenter;
import interview.veripark.com.ui.activity.splash.SplashMvpView;
import interview.veripark.com.ui.base.BasePresenter;
import interview.veripark.com.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mertKaradeniz on 9.11.2021
 * <p>
 * This is an interview project.
 */

public class DetailPresenter<V extends DetailMvpView> extends BasePresenter<V>
        implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager mDataManager, SchedulerProvider mSchedulerProvider, CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }


    @Override
    public void onHandleDetailRequest(DetailRequest request) {
        getCompositeDisposable().add(getDataManager()
                .doHandShakeStartApiCall(request.toJSONStringAndEncoded(getDataManager().getAesKey(), getDataManager().getAesVI()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    System.out.println(response.toString());


                    if (!isViewAttached()) {
                        return;
                    }

                }, throwable -> {

                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                }));
    }
}