package io.smit.demothis.rest.initialize;

import io.smit.demothis.rest.constants.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// This initializes only retrofit instance
// Makes it easy to scale
public final class RetrofitInitializer
{
    private static RetrofitInitializer retrofitInitializer= null;
    private static Retrofit retrofit = null;

    private RetrofitInitializer()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonInitializer.getGson()))
                .build();
    }

    private static RetrofitInitializer getInstance()
    {
        if (retrofitInitializer == null)
            retrofitInitializer = new RetrofitInitializer();
        return retrofitInitializer;
    }

    public static Retrofit getRetrofit()
    {
        getInstance();
        return retrofit;
    }
}
