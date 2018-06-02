package com.andreiarodrigues.adidasgoals.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import java.util.List;

/**
 * Created by andreia.rodrigues
 */
@Dao
public interface GoalsDao {

    @Query("SELECT * from goals")
    LiveData<List<GoalsModel>> getAllGoals();

    @Query("SELECT * FROM goals where id = :id")
    GoalsModel getGoal(int id);

    @Insert
    void insertGoal(GoalsModel goal);

    @Query("DELETE FROM goals")
    void deleteGoals();
}
