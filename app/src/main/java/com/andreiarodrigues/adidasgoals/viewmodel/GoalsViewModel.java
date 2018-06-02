package com.andreiarodrigues.adidasgoals.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.andreiarodrigues.adidasgoals.data.GoalsRepository;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import java.util.List;

/**
 * Created by andreia.rodrigues
 */
public class GoalsViewModel extends AndroidViewModel {

    private LiveData<List<GoalsModel>> listOfGoals;

    private GoalsRepository repository;

    public GoalsViewModel(Application application) {
        super(application);
        repository = new GoalsRepository(application);
        listOfGoals = repository.getAllGoals();
    }

    public LiveData<List<GoalsModel>> getAllGoals() {
        return listOfGoals;
    }

    public void insert(GoalsModel goal) {
        repository.insertGoal(goal);
    }

    public void deleteAllGoals() {
        repository.deleteAllGoals();
    }
}