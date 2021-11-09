package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import interview.veripark.com.utils.AESUtils;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class DetailRequest {

    @SerializedName("id")
    @Expose
    private Integer id;

    public DetailRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String toJSONStringAndEncoded(String aesKey, String aesVI) {
        JSONObject js = new JSONObject();
        try {
            byte[] encoded = AESUtils.encrypt(aesKey, aesVI, String.valueOf(getId()));
            js.put("id", AESUtils.converterByteToString(encoded));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
