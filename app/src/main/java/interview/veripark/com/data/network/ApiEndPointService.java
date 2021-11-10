package interview.veripark.com.data.network;

import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.data.network.model.StockResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface ApiEndPointService {

    @POST("/api/handshake/start")
    @Headers({"Content-Type: application/json"})
    Observable<HandShakeResponse> getEndPointHandShakeService(@Body String request);

    @POST("/api/stocks/list")
    @Headers({"Content-Type: application/json"})
    Observable<StockResponse> getEndPointStockService(@Body String request);

    @POST("/api/stocks/detail")
    @Headers({"Content-Type: application/json"})
    Observable<DetailResponse> getEndPointDetailService(@Body String request);

}
