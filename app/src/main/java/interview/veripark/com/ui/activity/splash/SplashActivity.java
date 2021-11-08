package interview.veripark.com.ui.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.data.network.model.HandShakeRequest;
import interview.veripark.com.ui.base.BaseActivity;
import interview.veripark.com.ui.main.MainActivity;
import interview.veripark.com.utils.DeviceAndSystemInfoUtils;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {


    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SplashActivity.this);

        mPresenter.onHandShakeStart(initHandShake());
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    private HandShakeRequest initHandShake() {
        return new HandShakeRequest(
                DeviceAndSystemInfoUtils.getDeviceId(SplashActivity.this),
                DeviceAndSystemInfoUtils.getAppVersionName(this),
                DeviceAndSystemInfoUtils.getPlatformName(),
                DeviceAndSystemInfoUtils.getDeviceName(),
                DeviceAndSystemInfoUtils.getManufacturer()
        );
    }
}
