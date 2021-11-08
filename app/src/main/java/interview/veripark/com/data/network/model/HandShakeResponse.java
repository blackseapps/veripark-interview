package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class HandShakeResponse {

    @Expose
    @SerializedName("aesKey")
    private String aesKey;

    @Expose
    @SerializedName("aesIV")
    private String aesIV;

    @Expose
    @SerializedName("authorization")
    private String authorization;

    @Expose
    @SerializedName("lifeTime")
    private String lifeTime;

    @Expose
    @SerializedName("status")
    private Status status;

    public HandShakeResponse(String aesKey, String aesIV, String authorization, String lifeTime, Status status) {
        this.aesKey = aesKey;
        this.aesIV = aesIV;
        this.authorization = authorization;
        this.lifeTime = lifeTime;
        this.status = status;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAesIV() {
        return aesIV;
    }

    public void setAesIV(String aesIV) {
        this.aesIV = aesIV;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandShakeResponse that = (HandShakeResponse) o;
        return Objects.equals(aesKey, that.aesKey) && Objects.equals(aesIV, that.aesIV) && Objects.equals(authorization, that.authorization) && Objects.equals(lifeTime, that.lifeTime) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aesKey, aesIV, authorization, lifeTime, status);
    }

    @Override
    public String toString() {
        return "HandShakeResponse{" +
                "aesKey='" + aesKey + '\'' +
                ", aesIV='" + aesIV + '\'' +
                ", authorization='" + authorization + '\'' +
                ", lifeTime='" + lifeTime + '\'' +
                ", status=" + status +
                '}';
    }
}
