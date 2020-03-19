package com.yulin.lotto.rest;

import retrofit2.Retrofit;

/**
 * Created by Yulin. I on 19,March,2020
 */
public class RetrofitInstance {
    private static Retrofit instance;
    public static final String BASE_URL = "https://www.pais.co.il/";

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

}
