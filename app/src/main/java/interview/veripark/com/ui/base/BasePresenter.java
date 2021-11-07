package interview.veripark.com.ui.base;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    private final DataManager mDataManager;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
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

    @Override
    public void onDetach() {
        mMvpView = null;
    }

}
