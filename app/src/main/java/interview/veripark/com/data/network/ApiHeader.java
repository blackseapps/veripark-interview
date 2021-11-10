package interview.veripark.com.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.veripark.com.di.AesKey;
import interview.veripark.com.di.AesVI;
import interview.veripark.com.di.ApiInfo;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(ProtectedApiHeader protectedApiHeader, PublicApiHeader publicApiHeader) {
        mProtectedApiHeader = protectedApiHeader;
        mPublicApiHeader = publicApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getmPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("aesKey")
        private String aesKey;

        @Expose
        @SerializedName("aesIV")
        private String aesIV;

        @Inject
        public PublicApiHeader(@AesKey String aesKey,@AesVI String aesIV) {
            this.aesKey = aesKey;
            this.aesIV = aesIV;
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
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("authorization")
        private String authorization;

        @Expose
        @SerializedName("aesKey")
        private String aesKey;

        @Expose
        @SerializedName("aesIV")
        private String aesIV;


        public ProtectedApiHeader(String authorization, String aesKey, String aesIV) {
            this.authorization = authorization;
            this.aesKey = aesKey;
            this.aesIV = aesIV;
        }

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
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
    }
}