package interview.veripark.com.ui.main;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.ui.base.BaseActivity;
import interview.veripark.com.ui.fragment.stock.StockAndIndexFragment;
import interview.veripark.com.utils.AESEncryptionAndDecryptionUtils;

/**
 * Created by mertKaradeniz on 6.11.2021
 * <p>
 * This is an interview project.
 */

public class MainActivity extends BaseActivity implements MainMvpView, NavigationView.OnNavigationItemSelectedListener {


    String aesKey = "TcikJHXCFEFfn/Xi2pinro+w9kkq5PVHJovKnpeoyiM=";
    String aesVI = "FytQBY45rlQCUBkthnsbew==";

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.my_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle actionBarDrawerToggle;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUpNavigation();
        setUp();

        String output = AESEncryptionAndDecryptionUtils.encrypt(aesKey, aesVI, "all");
        System.out.println(output);
    }

    @Override
    protected void setUp() {
    }



    public void setUpNavigation() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void showAddingFragment() {

    }

    @Override
    public void showListingFragment() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Bundle args = new Bundle();

        switch (item.getItemId()) {
            case R.id.stock_and_index:
                args.putString("value", getResources().getString(R.string.filter_all));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
            case R.id.increasing:
                args.putString("value", getResources().getString(R.string.filter_increasing));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
            case R.id.decreasing:
                args.putString("value", getResources().getString(R.string.filter_decreasing));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
            case R.id.volume30:
                args.putString("value", getResources().getString(R.string.filter_volume30));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
            case R.id.volume50:
                args.putString("value", getResources().getString(R.string.filter_volume50));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
            case R.id.volume100:
                args.putString("value", getResources().getString(R.string.filter_volume100));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
            default:
                args.putString("value", getResources().getString(R.string.filter_all));
                fragment = StockAndIndexFragment.newInstance(args);
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
