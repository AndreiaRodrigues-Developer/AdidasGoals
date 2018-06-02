package com.andreiarodrigues.adidasgoals;

import android.app.Application;
import android.content.Context;

import com.andreiarodrigues.adidasgoals.data.network.ApiRepository;

/**
 * Created by andreia.rodrigues
 */
public class AdidasGoalsApplication extends Application {

    private static ApiRepository API_REPOSITORY_INSTANCE;

    public static ApiRepository getApiRepositoryInstance(Context context) {
        if (API_REPOSITORY_INSTANCE == null) {
            API_REPOSITORY_INSTANCE =
                    new ApiRepository("https://thebigachallenge.appspot.com/_ah/api/myApi/v1/");
        }
        return API_REPOSITORY_INSTANCE;
    }

    public void destroyApiRepositoryInstance() {
        API_REPOSITORY_INSTANCE = null;
    }
}