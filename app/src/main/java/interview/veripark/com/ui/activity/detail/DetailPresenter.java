package interview.veripark.com.ui.activity.detail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.data.network.model.DetailRequest;
import interview.veripark.com.data.network.model.DetailResponse;
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
    public void onHandleDetailRequest(String value) {

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doDetailResponseApiCall(initDetailRequest(value).toJSONStringAndEncoded())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    System.out.println("graph: " + response.getGraphicData().get(0).getValue());

                    if (response != null && response.getSymbol() != null) {
                        getMvpView().updateDetailItemData(response);
                        getMvpView().updateDetailChartData(initGraphData(response.getGraphicData()));
                    }

                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                }, throwable -> {

                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                }));
    }

    private DetailRequest initDetailRequest(String value) {
        return new DetailRequest(getAesEncryptValue(value));
    }


    ArrayList<Float> initGraphData(List<DetailResponse.GraphicDatum> graphicDatum) {
        ArrayList<Float> data = new ArrayList<>();
        for (int i = 0; i < graphicDatum.size(); i++) {
            data.add(graphicDatum.get(i).getValue());
        }
        return data;
    }
}