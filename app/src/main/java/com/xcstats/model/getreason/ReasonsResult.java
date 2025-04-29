package com.xcstats.model.getreason;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReasonsResult {

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