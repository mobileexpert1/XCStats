package com.xcstats.model.viewtrackschdule;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewTrack implements Serializable {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("results")
@Expose
private List<Result> results = null;

public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public List<Result> getResults() {
return results;
}

public void setResults(List<Result> results) {
this.results = results;
}

}