package com.xcstats.model.getparents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

@SerializedName("parents")
@Expose
private List<Parent> parents = null;
@SerializedName("count_of_parents")
@Expose
private Integer countOfParents;

public List<Parent> getParents() {
return parents;
}

public void setParents(List<Parent> parents) {
this.parents = parents;
}

public Integer getCountOfParents() {
return countOfParents;
}

public void setCountOfParents(Integer countOfParents) {
this.countOfParents = countOfParents;
}

}


