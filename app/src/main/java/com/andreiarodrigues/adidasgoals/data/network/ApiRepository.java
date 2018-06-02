package com.andreiarodrigues.adidasgoals.data.network;

import android.support.annotation.NonNull;

import com.andreiarodrigues.adidasgoals.data.models.BaseResponse;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by andreia.rodrigues
 */
public class ApiRepository {

    private ApiService service;

    public ApiRepository(String baseUrl) {

        //Set up network connection
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
    }

    /**
     * Call to retrieve all goals
     *
     * @param cb {@see retrofit.Callback}<{@see BaseResponse}> The Callback of the request.
     */
    public void getGoals(@NonNull Callback<BaseResponse<GoalsModel>> cb) {
        Call<BaseResponse<GoalsModel>> call = service.getGoals();
        call.enqueue(cb);
    }

}
