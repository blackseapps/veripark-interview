package interview.veripark.com.ui.activity.detail;

import java.util.ArrayList;
import java.util.List;

import interview.veripark.com.data.network.model.DetailResponse;
import interview.veripark.com.ui.base.BaseMvpView;

/**
 * Created by mertKaradeniz on 9.11.2021
 * <p>
 * This is an interview project.
 */

public interface DetailMvpView extends BaseMvpView {

    void updateDetailItemData(DetailResponse detailResponse);

    void updateDetailChartData(ArrayList<Float> graphicDatum);

}
