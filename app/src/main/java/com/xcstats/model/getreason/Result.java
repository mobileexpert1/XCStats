package com.xcstats.model.getreason;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("label")
@Expose
private String label;
@SerializedName("value")
@Expose
private String value;

public String getLabel() {
return label;
}

public void setLabel(String label) {
this.label = label;
}

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

}