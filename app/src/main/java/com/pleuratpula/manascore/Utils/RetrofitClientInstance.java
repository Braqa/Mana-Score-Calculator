package com.pleuratpula.manascore.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
//    private static final String API_VERSION = "3/";
    private static final String BASE_URL = "https://www.boardgameatlas.com/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
