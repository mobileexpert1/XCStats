package com.xcstats.model.leadboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Radio {

@SerializedName("name")
@Expose
private String name;
@SerializedName("checked")
@Expose
private String checked;
@SerializedName("label")
@Expose
private String label;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getChecked() {
return checked;
}

public void setChecked(String checked) {
this.checked = checked;
}

public String getLabel() {
return label;
}

public void setLabel(String label) {
this.label = label;
}

}