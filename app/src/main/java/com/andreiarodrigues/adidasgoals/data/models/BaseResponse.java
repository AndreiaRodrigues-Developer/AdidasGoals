package com.andreiarodrigues.adidasgoals.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by andreia.rodrigues
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {

    @JsonProperty("items")
    private List<T> items;

    @JsonProperty("nextPageToken")
    private String nextPageToken;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
