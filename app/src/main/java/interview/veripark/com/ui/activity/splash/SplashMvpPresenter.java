package interview.veripark.com.ui.activity.splash;

import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.ui.base.BaseMvpPresenter;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface SplashMvpPresenter<V extends SplashMvpView> extends BaseMvpPresenter<V> {

    void onHandShakeStart(HandShakeRequest shakeRequest);
}
