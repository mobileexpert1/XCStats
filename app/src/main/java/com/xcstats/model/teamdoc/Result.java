package com.xcstats.model.teamdoc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("icon")
@Expose
private String icon;
@SerializedName("name")
@Expose
private String name;
@SerializedName("url")
@Expose
private String url;
@SerializedName("description")
@Expose
private String description;

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

}