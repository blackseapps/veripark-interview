package interview.veripark.com.ui.base;

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

}
