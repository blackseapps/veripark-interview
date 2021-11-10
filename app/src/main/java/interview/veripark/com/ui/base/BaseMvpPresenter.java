package interview.veripark.com.ui.base;

import interview.veripark.com.data.network.model.ApiError;
import interview.veripark.com.data.network.model.Status;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface BaseMvpPresenter<V extends BaseMvpView> {

    void onAttach(V mvpView);

    void onDetach();

    String getAesEncryptValue(String value);

    String getAesDecryptValue(String value);

    void handleApiError(Status error, CallBackApi callBackApi, String params);

    void handleApiError(String error);


    interface CallBackApi {
        void callBackFunc(String param);
    }
}
