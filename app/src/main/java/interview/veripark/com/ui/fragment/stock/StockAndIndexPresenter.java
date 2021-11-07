package interview.veripark.com.ui.fragment.stock;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.ui.base.BasePresenter;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class StockAndIndexPresenter<V extends StockAndIndexMvpView> extends BasePresenter<V>
        implements StockAndIndexMvpPresenter<V> {

    @Inject
    public StockAndIndexPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

}
