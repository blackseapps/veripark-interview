package interview.veripark.com.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.veripark.com.BuildConfig;
import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.data.network.model.StockResponse;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private Retrofit retrofit;

    private ApiHeader mApiHeader;


    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        this.mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ApiServiceInterceptor(mApiHeader.getProtectedApiHeader())).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Override
    public Observable<HandShakeResponse> doHandShakeStartApiCall(String request) {
        initRetrofit();
        return retrofit.create(ApiEndPointService.class).getEndPointHandShakeService(request);
    }

    @Override
    public Observable<StockResponse> doStockResponseApiCall(String request) {
        initRetrofit();
        return retrofit.create(ApiEndPointService.class).getEndPointStockService(request);
    }

    @Override
    public Observable<DetailResponse> doDetailResponseApiCall(String request) {
        return retrofit.create(ApiEndPointService.class).getEndPointDetailService(request);
    }
}
