package interview.veripark.com.data.network;

import interview.veripark.com.data.network.model.DetailRequest;
import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.data.network.model.StockRequest;
import interview.veripark.com.data.network.model.StockResponse;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<HandShakeResponse> doHandShakeStartApiCall(String request);

    Observable<StockResponse> doStockResponseApiCall(String request);

    Observable<DetailResponse> doDetailResponseApiCall(String request);

}
