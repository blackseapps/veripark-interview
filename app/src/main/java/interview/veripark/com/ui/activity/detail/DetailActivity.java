package interview.veripark.com.ui.activity.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.ui.activity.splash.SplashActivity;
import interview.veripark.com.ui.base.BaseActivity;
import interview.veripark.com.ui.component.LineChartDetail;

/**
 * Created by mertKaradeniz on 9.11.2021
 * <p>
 * This is an interview project.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {


    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;

    @BindView(R.id.chart1)
    LineChart chart;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        LineChartDetail detail = new LineChartDetail(this, chart);
        detail.initChart();
        float[] range = new float[]{10, 20, 30, 40, 50};
        detail.setData(range);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
    }
}