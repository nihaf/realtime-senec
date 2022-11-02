package de.codematrosen.rts.infrastructure;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SenecServiceGenerator {

    // TODO value from shared preferences
    public static <S> S createService(String baseUrl, Class<S> serviceClass) {
        Retrofit.Builder retrofitBuilder = builder(baseUrl);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
        if (!httpClientBuilder.interceptors().contains(logging)) {
            httpClientBuilder.addInterceptor(logging);
            retrofitBuilder.client(httpClientBuilder.build());
        }
        return retrofitBuilder.build().create(serviceClass);
    }

    private static Retrofit.Builder builder(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
    }
}
