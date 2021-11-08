package interview.veripark.com.data.prefs;

import interview.veripark.com.data.DataManager;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface PreferencesHelper {

    String getAuthorizationToken();

    void setAuthorizationToken(String token);

    String getAesKey();

    void setAesKey(String key);

    String getAesVI();

    void setAesVI(String vi);


}
