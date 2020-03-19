package com.yulin.lotto.rest;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface GetWinsListService {
    @Streaming
    @GET("Lotto/lotto_resultsDownload.aspx")
    Call<ResponseBody> getWinsCSV();
}
