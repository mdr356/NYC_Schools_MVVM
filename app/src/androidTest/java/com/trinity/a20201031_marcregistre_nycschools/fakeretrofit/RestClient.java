package com.trinity.a20201031_marcregistre_nycschools.fakeretrofit;

import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {

    private static RetrofitApi mRestService = null;

    public static RetrofitApi getClient() {
        if(mRestService == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                                  .addInterceptor(new FakeInterceptor())
                                                  .build();

            final Retrofit retrofit = new Retrofit.Builder()
                                          .addConverterFactory(GsonConverterFactory.create())
                                          // Endpoint
                                          .baseUrl("https://data.cityofnewyork.us/")
                                          .client(okHttpClient)
                                          .build();

            mRestService = retrofit.create(RetrofitApi.class);
        }
        return mRestService;
    }
}
