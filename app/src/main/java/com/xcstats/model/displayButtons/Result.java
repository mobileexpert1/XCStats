package com.xcstats.model.displayButtons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("show_track")
    @Expose
    private Integer showTrack;
    @SerializedName("show_xc")
    @Expose
    private Integer showXc;

    public Integer getShowTrack() {
        return showTrack;
    }

    public void setShowTrack(Integer showTrack) {
        this.showTrack = showTrack;
    }

    public Integer getShowXc() {
        return showXc;
    }

    public void setShowXc(Integer showXc) {
        this.showXc = showXc;
    }

    @SerializedName("count")
    @Expose
    private
    Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    @SerializedName("bg")
    @Expose
    private
    String bg;

}