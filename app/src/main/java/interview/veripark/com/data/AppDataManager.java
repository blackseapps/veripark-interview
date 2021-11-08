package interview.veripark.com.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.veripark.com.data.network.ApiHeader;
import interview.veripark.com.data.network.ApiHelper;
import interview.veripark.com.data.network.model.DetailRequest;
import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.data.network.model.HandShakeResponse;
import interview.veripark.com.data.network.model.StockRequest;
import interview.veripark.com.data.network.model.StockResponse;
import interview.veripark.com.data.prefs.PreferencesHelper;
import interview.veripark.com.di.ApplicationContext;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context mContext, ApiHelper mApiHelper, PreferencesHelper mPreferencesHelper) {
        this.mContext = mContext;
        this.mApiHelper = mApiHelper;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<HandShakeResponse> doHandShakeStartApiCall(String request) {
        return mApiHelper.doHandShakeStartApiCall(request);
    }

    @Override
    public Observable<StockResponse> doStockResponseApiCall(String request) {
        return mApiHelper.doStockResponseApiCall(request);
    }

    @Override
    public Observable<DetailResponse> doDetailResponseApiCall(String request) {
        return mApiHelper.doDetailResponseApiCall(request);
    }


    @Override
    public String getAuthorizationToken() {
        return mPreferencesHelper.getAuthorizationToken();
    }

    @Override
    public void setAuthorizationToken(String token) {
        mPreferencesHelper.setAuthorizationToken(token);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(token);
    }

    @Override
    public String getAesKey() {
        return mPreferencesHelper.getAesKey();
    }

    @Override
    public void setAesKey(String key) {
        mPreferencesHelper.setAesKey(key);
    }

    @Override
    public String getAesVI() {
        return mPreferencesHelper.getAesVI();
    }

    @Override
    public void setAesVI(String vi) {
        mPreferencesHelper.setAesVI(vi);
    }

    @Override
    public void updateApiHeader(String aesKey, String aesVI, String token) {
        mPreferencesHelper.setAuthorizationToken(token);
        mPreferencesHelper.setAesKey(aesKey);
        mPreferencesHelper.setAesVI(aesVI);

        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(token);
    }
}
