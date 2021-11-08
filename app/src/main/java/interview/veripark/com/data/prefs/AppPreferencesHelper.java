package interview.veripark.com.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.veripark.com.di.ApplicationContext;
import interview.veripark.com.di.PreferenceInfo;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_AES_KEY = "PREF_KEY_AES_KEY";
    private static final String PREF_KEY_AES_VI = "PREF_KEY_AES_VI";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAuthorizationToken() {
         return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAuthorizationToken(String token) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, token).apply();
    }

    @Override
    public String getAesKey() {
        return mPrefs.getString(PREF_KEY_AES_KEY, null);
    }

    @Override
    public void setAesKey(String key) {
        mPrefs.edit().putString(PREF_KEY_AES_KEY, key).apply();
    }

    @Override
    public String getAesVI() {
        return mPrefs.getString(PREF_KEY_AES_VI, null);
    }

    @Override
    public void setAesVI(String vi) {
        mPrefs.edit().putString(PREF_KEY_AES_VI, vi).apply();
    }
}
