package com.xcstats.model.leadboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenderSelect {

@SerializedName("name")
@Expose
private String name;
@SerializedName("options")
@Expose
private List<Option_> options = null;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public List<Option_> getOptions() {
return options;
}

public void setOptions(List<Option_> options) {
this.options = options;
}

}