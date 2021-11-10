package interview.veripark.com.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class DeviceAndSystemInfoUtils {

    private static DeviceAndSystemInfoUtils single_instance = null;

    private Context context;

    private DeviceAndSystemInfoUtils(Context context) {
        this.context = context;
    }

    public static DeviceAndSystemInfoUtils getInstance(Context context) {
        if (single_instance == null)
            single_instance = new DeviceAndSystemInfoUtils(context);
        return single_instance;
    }

    @SuppressLint("HardwareIds")
    public String getDeviceId() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getAppVersionName() {
        String versionName = "1.0.0";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public String getPlatformName() {
        return "Android";
    }

    public String getDeviceName() {
        String model = Build.MODEL;
        return capitalize(model);
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
