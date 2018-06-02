package com.andreiarodrigues.adidasgoals.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.andreiarodrigues.adidasgoals.data.database.AdidasGoalsDatabase;
import com.andreiarodrigues.adidasgoals.data.database.GoalsDao;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import java.util.List;

/**
 * Created by andreia.rodrigues
 */
public class GoalsRepository {

    private GoalsDao goalsDao;
    private LiveData<List<GoalsModel>> listOfGoals;

    public GoalsRepository(Application application) {
        AdidasGoalsDatabase database = AdidasGoalsDatabase.getDatabaseInstance(application);
        this.goalsDao = database.goalsDao();
        listOfGoals = goalsDao.getAllGoals();
    }

    public LiveData<List<GoalsModel>> getAllGoals() {
        return listOfGoals;
    }

    public void insertGoal(GoalsModel goal) {
        new insertAsyncTask(goalsDao).execute(goal);
    }

    public void deleteAllGoals() {
        new deleteAsyncTask(goalsDao).execute();
    }

    /**
     * AsyncTaks to insert goal in an asynchronous way
     */
    private static class insertAsyncTask extends AsyncTask<GoalsModel, Void, Void> {

        private GoalsDao myAsyncTaskDao;

        insertAsyncTask(GoalsDao dao) {
            myAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GoalsModel... params) {
            myAsyncTaskDao.insertGoal(params[0]);
            return null;
        }
    }

    /**
     * AsyncTaks to delete all goal in an asynchronous way
     */
    private static class deleteAsyncTask extends AsyncTask<GoalsModel, Void, Void> {

        private GoalsDao myAsyncTaskDao;

        deleteAsyncTask(GoalsDao dao) {
            myAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GoalsModel... params) {
            myAsyncTaskDao.deleteGoals();
            return null;
        }
    }
}
