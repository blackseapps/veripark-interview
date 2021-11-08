package interview.veripark.com.data;

import interview.veripark.com.data.network.ApiHelper;
import interview.veripark.com.data.prefs.PreferencesHelper;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public interface DataManager extends ApiHelper, PreferencesHelper {

    void updateApiHeader(String aesKey, String aesVI, String accessToken);

}
