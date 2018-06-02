package com.andreiarodrigues.adidasgoals.data.network;

import com.andreiarodrigues.adidasgoals.data.models.BaseResponse;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andreia.rodrigues
 */
public interface ApiService {

    @GET("goals")
    Call<BaseResponse<GoalsModel>> getGoals();
}