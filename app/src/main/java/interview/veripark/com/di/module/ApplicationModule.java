package interview.veripark.com.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.veripark.com.data.AppDataManager;
import interview.veripark.com.data.DataManager;
import interview.veripark.com.data.network.ApiHeader;
import interview.veripark.com.data.network.ApiHelper;
import interview.veripark.com.data.network.AppApiHelper;
import interview.veripark.com.data.prefs.AppPreferencesHelper;
import interview.veripark.com.data.prefs.PreferencesHelper;
import interview.veripark.com.di.ApiInfo;
import interview.veripark.com.di.ApplicationContext;
import interview.veripark.com.di.PreferenceInfo;
import interview.veripark.com.utils.AppConstants;

/**
 * Created by mertKaradeniz on 6.11.2021
 * <p>
 * This is an interview project.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return "";
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey) {
        return new ApiHeader.ProtectedApiHeader(apiKey);
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }


}