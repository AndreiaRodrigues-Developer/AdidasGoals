package com.andreiarodrigues.adidasgoals.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

/**
 * Created by andreia.rodrigues
 */
@Database(entities = {GoalsModel.class}, version = 1, exportSchema = false)
public abstract class AdidasGoalsDatabase extends RoomDatabase {

    private static AdidasGoalsDatabase INSTANCE;

    public static AdidasGoalsDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AdidasGoalsDatabase.class,
                            "adidasgoals-db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    public void destroyInstance() {
        INSTANCE = null;
    }

    public abstract GoalsDao goalsDao();

}
