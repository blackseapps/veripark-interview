package interview.veripark.com.ui.fragment.stock;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class StockAndIndexFragment extends BaseFragment implements StockAndIndexMvpView {

    public static final String TAG = "AddingFragment";


    @Inject
    StockAndIndexMvpPresenter<StockAndIndexMvpView> mPresenter;

    @Inject
    StockAdapter listingAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.stockRecyclerView)
    RecyclerView mRecyclerView;

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
        String data = getBundleData("value");

        Log.i("Data", data);

        mPresenter.onHandleStockRequest(initStockRequest(data));
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(listingAdapter);
    }


    @Override
    public void openActivityOnTokenExpire() {

    }

    private StockRequest initStockRequest(String value) {
        return new StockRequest(value);
    }

    @Override
    public void updateStocks(List<StockResponse.Stock> response) {

        listingAdapter.addItems(response);

        // parentLayout.addView(createTableLayout(getDataInit(), 7));

    }


    private String[][] getDataInit(List<StockResponse.Stock> extraData) {
        String[][] data = new String[][]{{"Sembol", "Fiyat", "Fark", "Hacim", "Alış", "Satış", "Değişim"}};

        String[][] tempData = null;


        // Collections.addAll(data, new String[][]);


        return data;
    }


    private TableLayout createTableLayout(String[][] data, int columnCount) {
        // 1) Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(getContext());
        //  tableLayout.setBackgroundColor(Color.BLACK);

        // 2) create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.weight = 1;

        for (int i = 0; i < data.length; i++) {
            // 3) create tableRow
            TableRow tableRow = new TableRow(getContext());
            // tableRow.setBackgroundColor(Color.BLACK);
            for (int j = 0; j < columnCount; j++) {
                // 4) create textView
                TextView textView = new TextView(getContext());
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(10, 10, 10, 10);
                //     textView.setBackground(getResources().getDrawable(R.drawable.textview_border));
                textView.setText(data[i][j]);
                if (i == 0) {
                    textView.setBackgroundColor(Color.parseColor("#aeaeae"));
                }
                tableRow.addView(textView, tableRowParams);
            }
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
    }
}