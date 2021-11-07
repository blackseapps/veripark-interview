package interview.veripark.com.ui.main;

import javax.inject.Inject;

import interview.veripark.com.data.DataManager;
import interview.veripark.com.ui.base.BasePresenter;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void onOpenAddingFragment() {
        getMvpView().showAddingFragment();
    }

    @Override
    public void onOpenListingFragment() {
        getMvpView().showListingFragment();
    }
}
