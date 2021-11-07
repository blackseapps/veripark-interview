package interview.veripark.com.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;

import interview.veripark.com.di.ApiInfo;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("authorization")
        private String authorization;

        @Inject
        public PublicApiHeader(@ApiInfo String authorization) {
            this.authorization = authorization;
        }

        public String getApiKey() {
            return authorization;
        }

        public void setApiKey(String authorization) {
            this.authorization = authorization;
        }
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("authorization")
        private String authorization;

        public ProtectedApiHeader(String authorization) {
            this.authorization = authorization;
        }

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }
    }
}