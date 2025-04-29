package com.xcstats.model.plannedabsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlannedAbsenseResult {

@SerializedName("success")
@Expose
private Boolean success;

@SerializedName("results")
@Expose
private Results results;

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    private Summary summary;




public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public Results getResults() {
return results;
}

public void setResults(Results results) {
this.results = results;
}
}