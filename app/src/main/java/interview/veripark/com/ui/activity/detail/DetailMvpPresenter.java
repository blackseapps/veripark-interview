package interview.veripark.com.ui.activity.detail;

import interview.veripark.com.ui.base.BaseMvpPresenter;

/**
 * Created by mertKaradeniz on 9.11.2021
 * <p>
 * This is an interview project.
 */

public interface DetailMvpPresenter<V extends DetailMvpView> extends BaseMvpPresenter<V> {

    void onHandleDetailRequest(String request);
}
