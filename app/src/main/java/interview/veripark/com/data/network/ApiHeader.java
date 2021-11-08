package interview.veripark.com.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.veripark.com.di.ApiInfo;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;

    @Inject
    public ApiHeader( ProtectedApiHeader protectedApiHeader) {
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
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