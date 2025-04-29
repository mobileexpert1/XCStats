package com.xcstats.model.GoalTrack;

import java.util.ArrayList;

/**
 * Created by Mobile on 1/11/2017.
 */

public class GoalDetailTrackField {

    private String shrt;
    private String longi;
    private ArrayList<SelectEventData> list = new ArrayList<>();

    public String getShrt() {
        return shrt;
    }

    public void setShrt(String shrt) {
        this.shrt = shrt;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public ArrayList<SelectEventData> getList() {
        return list;
    }

    public void setList(ArrayList<SelectEventData> list) {
        this.list = list;
    }
}
