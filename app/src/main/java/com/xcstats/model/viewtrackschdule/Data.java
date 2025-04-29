package com.xcstats.model.viewtrackschdule;


public class Data {
    private String meet_date;

    public String getMeet_date() {
        return meet_date;
    }

    public void setMeet_date(String meet_date) {
        this.meet_date = meet_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String    name;
    private String location;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}