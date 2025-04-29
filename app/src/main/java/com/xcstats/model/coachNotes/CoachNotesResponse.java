package com.xcstats.model.coachNotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoachNotesResponse {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("results")
@Expose
private Results results;

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