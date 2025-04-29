package com.xcstats.model.GetLeader;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

@SerializedName("rank")
@Expose
private Integer rank;
@SerializedName("athlete")
@Expose
private String athlete;
@SerializedName("gr")
@Expose
private String gr;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @SerializedName("val")
@Expose
private String val;

public Integer getRank() {
return rank;
}

public void setRank(Integer rank) {
this.rank = rank;
}

public String getAthlete() {
return athlete;
}

public void setAthlete(String athlete) {
this.athlete = athlete;
}

public String getGr() {
return gr;
}

public void setGr(String gr) {
this.gr = gr;
}



}