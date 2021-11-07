package interview.veripark.com.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import butterknife.ButterKnife;
import interview.veripark.com.R;
import interview.veripark.com.ui.base.BaseActivity;
import interview.veripark.com.utils.AESEncryptionAndDecryptionUtils;

/**
 * Created by mertKaradeniz on 6.11.2021
 * <p>
 * This is an interview project.
 */

public class MainActivity extends BaseActivity implements MainMvpView {


    String aesKey = "TcikJHXCFEFfn/Xi2pinro+w9kkq5PVHJovKnpeoyiM=";
    String aesVI = "FytQBY45rlQCUBkthnsbew==";

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

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
        setUp();

        String output = AESEncryptionAndDecryptionUtils.encrypt(aesKey, aesVI, "all");
        System.out.println(output);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void showAddingFragment() {

    }

    @Override
    public void showListingFragment() {

    }
}
