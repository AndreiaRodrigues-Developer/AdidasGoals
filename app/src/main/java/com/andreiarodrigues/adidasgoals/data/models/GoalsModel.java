package com.andreiarodrigues.adidasgoals.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andreia.rodrigues
 */
@Entity(tableName = "goals")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoalsModel {

    @PrimaryKey
    @JsonProperty("id")
    @NonNull
    private String id = "";

    @ColumnInfo(name = "title")
    @JsonProperty("title")
    private String title;

    @ColumnInfo(name = "description")
    @JsonProperty("description")
    private String description;

    @ColumnInfo(name = "type")
    @JsonProperty("type")
    private String type;

    @ColumnInfo(name = "goal")
    @JsonProperty("goal")
    private int goal;

    public GoalsModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

}




