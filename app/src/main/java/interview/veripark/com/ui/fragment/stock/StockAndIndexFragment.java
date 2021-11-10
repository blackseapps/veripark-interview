package interview.veripark.com.ui.fragment.stock;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.data.network.model.StockRequest;
import interview.veripark.com.data.network.model.StockResponse;
import interview.veripark.com.di.component.ActivityComponent;
import interview.veripark.com.ui.activity.splash.SplashActivity;
import interview.veripark.com.ui.base.BaseFragment;
import interview.veripark.com.utils.DeviceAndSystemInfoUtils;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class StockAndIndexFragment extends BaseFragment implements StockAndIndexMvpView, StockAdapter.Callback {

    public static final String TAG = "AddingFragment";


    @Inject
    StockAndIndexMvpPresenter<StockAndIndexMvpView> mPresenter;

    @Inject
    StockAdapter stockAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.stockRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.search_bar)
    EditText search_bar;


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
            stockAdapter.setCallback(this);
            setHasOptionsMenu(true);
        }
        String data = getBundleData("value");

        mPresenter.onHandleStockRequest(data);
        return view;
    }

    @Override
    protected void setUp(View view) {

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0)
                    stockAdapter.getFilter().filter(s);
            }
        });

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(stockAdapter);
    }


    @Override
    public void openActivityOnTokenExpire() {

    }


    @Override
    public void updateStocks(List<StockResponse.Stock> response) {
        stockAdapter.addItems(response);
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onItemClick(StockResponse.Stock product) {

    }

    @Override
    public String getAesDecrypt(String value) {
        return mPresenter.getAesDecryptValue(value);
    }


}