package com.pleuratpula.manascore.Models;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ManaScoreService {
    @GET("ResultGames")
    Call<List<ResultGame>> resultsRepos();

    @POST("ResultGames")
    Call<ResultGame> saveResult(@Body ResultGame transaction);
}
