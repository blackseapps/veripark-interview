package interview.veripark.com.ui.fragment.stock;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.di.component.ActivityComponent;
import interview.veripark.com.ui.base.BaseFragment;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class StockAndIndexFragment extends BaseFragment implements StockAndIndexMvpView {

    public static final String TAG = "AddingFragment";


    @Inject
    StockAndIndexMvpPresenter<StockAndIndexMvpView> mPresenter;

    public static StockAndIndexFragment newInstance(Bundle args) {
        StockAndIndexFragment fragment = new StockAndIndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_stock_and_index, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            setHasOptionsMenu(true);
        }

        Log.i("Data", getBundleData("value"));

        return view;
    }

    @Override
    protected void setUp(View view) {
    }


    @Override
    public void openActivityOnTokenExpire() {

    }
}