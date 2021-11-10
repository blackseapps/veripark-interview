package interview.veripark.com.ui.base;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.utils.AESUtils;
import interview.veripark.com.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

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

}
