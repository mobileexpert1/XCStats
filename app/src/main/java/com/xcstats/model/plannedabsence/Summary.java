package com.xcstats.model.plannedabsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {

@SerializedName("summary")
@Expose
private Summary_ summary;

public Summary_ getSummary() {
return summary;
}

public void setSummary(Summary_ summary) {
this.summary = summary;
}

}