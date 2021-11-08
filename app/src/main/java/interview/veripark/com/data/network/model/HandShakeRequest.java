package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class HandShakeRequest {

    @Expose
    @SerializedName("deviceId")
    private String deviceId;

    @Expose
    @SerializedName("systemVersion")
    private String systemVersion;

    @Expose
    @SerializedName("platformName")
    private String platformName;

    @Expose
    @SerializedName("deviceModel")
    private String deviceModel;

    @Expose
    @SerializedName("manifacture")
    private String manifacturer;


    public HandShakeRequest(String deviceId, String systemVersion, String platformName, String deviceModel, String manifacturer) {
        this.deviceId = deviceId;
        this.systemVersion = systemVersion;
        this.platformName = platformName;
        this.deviceModel = deviceModel;
        this.manifacturer = manifacturer;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getManifacturer() {
        return manifacturer;
    }

    public void setManifacturer(String manifacturer) {
        this.manifacturer = manifacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandShakeRequest that = (HandShakeRequest) o;
        return Objects.equals(deviceId, that.deviceId) && Objects.equals(systemVersion, that.systemVersion) && Objects.equals(platformName, that.platformName) && Objects.equals(deviceModel, that.deviceModel) && Objects.equals(manifacturer, that.manifacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, systemVersion, platformName, deviceModel, manifacturer);
    }

    public String toJSONString() {
        JSONObject js = new JSONObject();
        try {
            js.put("deviceId", getDeviceId());
            js.put("systemVersion", getSystemVersion());
            js.put("platformName", getPlatformName());
            js.put("deviceModel", getDeviceModel());
            js.put("manifacturer", getManifacturer());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
