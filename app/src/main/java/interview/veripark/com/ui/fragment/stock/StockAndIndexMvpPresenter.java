package interview.veripark.com.ui.fragment.stock;

import interview.veripark.com.data.network.model.StockRequest;
import interview.veripark.com.ui.base.BaseMvpPresenter;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface StockAndIndexMvpPresenter<V extends StockAndIndexMvpView> extends BaseMvpPresenter<V> {

    void onHandleStockRequest(String stockRequest);
}