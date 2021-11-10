package interview.veripark.com.ui.activity.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.data.network.ApiHeader;
import interview.veripark.com.data.network.model.DetailRequest;
import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.data.network.model.StockRequest;
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

    @BindView(R.id.isUp)
    ImageView isUp;

    @BindView(R.id.change)
    TextView change;

    @BindView(R.id.offer)
    TextView offer;

    @BindView(R.id.highest)
    TextView highest;

    @BindView(R.id.lowest)
    TextView lowest;

    @BindView(R.id.maximum)
    TextView maximum;

    @BindView(R.id.minimum)
    TextView minimum;

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.volume)
    TextView volume;

    @BindView(R.id.symbol)
    TextView symbol;

    @BindView(R.id.count)
    TextView count;

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

        String Id = getIntentData("Id");
        mPresenter.onHandleDetailRequest(Id);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void updateDetailItemData(DetailResponse detailResponse) {

        isUp.setBackground(detailResponse.isUp() ? getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24)
                : getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24));

        change.setText(getResources().getString(R.string.change_text) + ": " + detailResponse.getChannge());
        offer.setText(getResources().getString(R.string.offer_text) + ": " + detailResponse.getOffer());
        highest.setText(getResources().getString(R.string.highest_text) + ": " + detailResponse.getHighest());
        lowest.setText(getResources().getString(R.string.lowest_text) + ": " + detailResponse.getLowest());
        count.setText(getResources().getString(R.string.count_text) + ": " + detailResponse.getCount());
        maximum.setText(getResources().getString(R.string.maximum_text) + ": " + detailResponse.getMaximum());
        minimum.setText(getResources().getString(R.string.minimum_text) + ": " + detailResponse.getMinimum());
        price.setText(getResources().getString(R.string.price_text) + ": " + detailResponse.getPrice());
        volume.setText(getResources().getString(R.string.volume_text) + ": " + detailResponse.getVolume());
        symbol.setText(getResources().getString(R.string.symbol_text) + ": " + mPresenter.getAesDecryptValue(detailResponse.getSymbol()));
    }

    @Override
    public void updateDetailChartData(ArrayList<Float> graphicDatum) {
        LineChartDetail detail = new LineChartDetail(this, chart);
        detail.initChart();
        float[] range = new float[]{10, 20, 30, 40, 50};
        detail.setData(graphicDatum);
    }


}