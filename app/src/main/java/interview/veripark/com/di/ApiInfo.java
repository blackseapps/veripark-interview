package interview.veripark.com.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInfo {
}