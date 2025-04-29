package com.xcstats.model.uploadimage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadResult {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("results")
@Expose
private String results;

public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public String getResults() {
return results;
}

public void setResults(String results) {
this.results = results;
}

}