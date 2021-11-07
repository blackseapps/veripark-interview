package interview.veripark.com.data;

import javax.inject.Inject;

import interview.veripark.com.data.network.ApiHelper;
import interview.veripark.com.data.network.model.DetailRequest;
import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.data.network.model.StockRequest;
import interview.veripark.com.data.network.model.StockResponse;
import io.reactivex.Single;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(ApiHelper mApiHelper) {
        this.mApiHelper = mApiHelper;
    }


    @Override
    public Single<HandShakeResponse> doHandShakeStartApiCall(HandShakeRequest request) {
        return null;
    }

    @Override
    public Single<StockResponse> doStockResponseApiCall(StockRequest request) {
        return null;
    }

    @Override
    public Single<DetailResponse> doDetailResponseApiCall(DetailRequest request) {
        return null;
    }
}
