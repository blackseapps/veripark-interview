package interview.veripark.com.ui.fragment.stock;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.data.network.model.StockRequest;
import interview.veripark.com.ui.base.BasePresenter;
import interview.veripark.com.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class StockAndIndexPresenter<V extends StockAndIndexMvpView> extends BasePresenter<V>
        implements StockAndIndexMvpPresenter<V> {

    @Inject
    public StockAndIndexPresenter(DataManager mDataManager, SchedulerProvider mSchedulerProvider, CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }

    @Override
    public void onHandleStockRequest(StockRequest stockRequest) {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doStockResponseApiCall(stockRequest.toJSONStringAndEncoded(getDataManager().getAesKey(), getDataManager().getAesVI()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    System.out.println(response.getStocks().get(0).getPrice());
                    System.out.println(response.getStatus().getError().getMessage());

                    if (response != null && response.getStocks() != null) {
                        getMvpView().updateStocks(response.getStocks());
                    }

                   /* getDataManager().updateApiHeader(
                            response.getAesKey(),
                            response.getAesIV(),
                            response.getAuthorization()
                    );*/

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
}
