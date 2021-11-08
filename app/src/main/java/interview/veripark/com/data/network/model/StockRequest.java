package interview.veripark.com.data.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.bouncycastle.util.encoders.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import interview.veripark.com.utils.AESUtils;

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

    public String toJSONStringAndEncoded(String aesKey, String aesVI) {
        JSONObject js = new JSONObject();
        try {
            byte[] encoded = AESUtils.encrypt(aesKey, aesVI, getPeriod());
            js.put("period", AESUtils.converterByteToString(encoded));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
