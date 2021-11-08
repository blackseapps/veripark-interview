package interview.veripark.com.data.network;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.veripark.com.data.network.ApiHeader;
import interview.veripark.com.data.prefs.PreferencesHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mertKaradeniz on 8.11.2021
 * <p>
 * This is an interview project.
 */


public class ApiServiceInterceptor implements Interceptor {

    private ApiHeader.ProtectedApiHeader protectedApiHeader;


    public ApiServiceInterceptor(ApiHeader.ProtectedApiHeader protectedApiHeader) {
        this.protectedApiHeader = protectedApiHeader;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("X-VP-Authorization", protectedApiHeader.getAuthorization())
                .build();
        return chain.proceed(newRequest);
    }
}