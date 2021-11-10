package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class DetailRequest {

    @SerializedName("id")
    @Expose
    private String id;

    public DetailRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(int String) {
        this.id = id;
    }


    public String toJSONStringAndEncoded() {
        JSONObject js = new JSONObject();
        try {
            js.put("id", getId());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
