package interview.veripark.com.ui.base;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import interview.veripark.com.R;
import interview.veripark.com.data.DataManager;
import interview.veripark.com.data.network.model.ApiError;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.data.network.model.Status;
import interview.veripark.com.ui.activity.splash.SplashActivity;
import interview.veripark.com.utils.AESUtils;
import interview.veripark.com.utils.AppConstants;
import interview.veripark.com.utils.DeviceAndSystemInfoUtils;
import interview.veripark.com.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    CallBackApi callBackApi;

    @Inject
    public BasePresenter(DataManager mDataManager, SchedulerProvider mSchedulerProvider, CompositeDisposable mCompositeDisposable) {
        this.mDataManager = mDataManager;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public String getAesEncryptValue(String value) {
        String output = "";
        try {
            byte[] encoded = AESUtils.encrypt(getDataManager().getAesKey(), getDataManager().getAesVI(), value);
            output = AESUtils.converterByteToString(encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getAesDecryptValue(String cipherText) {
        String output = "";
        try {
            byte[] aesKeyBytes = android.util.Base64.decode(cipherText, android.util.Base64.DEFAULT);
            output = AESUtils.decrypt(getDataManager().getAesKey(), getDataManager().getAesVI(), aesKeyBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public void handleApiError(Status error, CallBackApi callBackApi, String params) {

        this.callBackApi = callBackApi;

        if (error == null || error.getError().getMessage() == null) {
            getMvpView().onError(R.string.error_message);
            return;
        }

        try {

            switch (error.getError().getCode()) {
                case AppConstants.HTTP_UNAUTHORIZED:
                    getMvpView().onError("Authorization Token Updated.");
                    updateApiHeader(params);
                    break;
                default:
                    getMvpView().onError(error.getError().getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            // getMvpView().onError(R.string.api_default_error);
        }
    }

    @Override
    public void handleApiError(String error) {
        if (error != null) {
            getMvpView().onError(error);
        }
    }


    void updateApiHeader(String params) {
        getCompositeDisposable().add(getDataManager()
                .doHandShakeStartApiCall(initHandShake().toJSONString())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    System.out.println("updateToken:" + response.toString());

                    getDataManager().updateApiHeader(
                            response.getAesKey(),
                            response.getAesIV(),
                            response.getAuthorization()
                    );

                    callBackApi.callBackFunc(params);
                }, throwable -> {
                }));
    }

    private HandShakeRequest initHandShake() {
        return new HandShakeRequest(
                DeviceAndSystemInfoUtils.getInstance(null).getDeviceId(),
                DeviceAndSystemInfoUtils.getInstance(null).getAppVersionName(),
                DeviceAndSystemInfoUtils.getInstance(null).getPlatformName(),
                DeviceAndSystemInfoUtils.getInstance(null).getDeviceName(),
                DeviceAndSystemInfoUtils.getInstance(null).getManufacturer()
        );
    }

}
