package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import interview.veripark.com.utils.AESEncryptionAndDecryptionUtils;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class StockRequest {

    @SerializedName("period")
    @Expose
    private String period;

    public StockRequest(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String toJSONString(String aesKey, String aesVI) {
        JSONObject js = new JSONObject();
        try {
            js.put("period", AESEncryptionAndDecryptionUtils.encrypt(aesKey, aesVI, getPeriod()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
