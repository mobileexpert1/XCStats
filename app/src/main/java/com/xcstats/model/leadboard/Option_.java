package com.xcstats.model.leadboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option_ {

@SerializedName("value")
@Expose
private String value;
@SerializedName("label")
@Expose
private String label;

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

public String getLabel() {
return label;
}

public void setLabel(String label) {
this.label = label;
}

}