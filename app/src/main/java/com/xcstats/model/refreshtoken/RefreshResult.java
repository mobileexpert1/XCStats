package com.xcstats.model.refreshtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RefreshResult {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("results")
    @Expose
    private Results results = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
