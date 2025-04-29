package com.xcstats.model.crossgoaldetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Goal {



        @SerializedName("goal_time")
        @Expose
        private String goalTime;
        @SerializedName("notes_before")
        @Expose
        private String notesBefore;
        @SerializedName("MinutesField")
        @Expose
        private Integer minutesField;
        @SerializedName("SecondsField")
        @Expose
        private Integer secondsField;
        @SerializedName("pace")
        @Expose
        private String pace;

        public String getGoalTime() {
            return goalTime;
        }

        public void setGoalTime(String goalTime) {
            this.goalTime = goalTime;
        }

        public String getNotesBefore() {
            return notesBefore;
        }

        public void setNotesBefore(String notesBefore) {
            this.notesBefore = notesBefore;
        }

        public Integer getMinutesField() {
            return minutesField;
        }

        public void setMinutesField(Integer minutesField) {
            this.minutesField = minutesField;
        }

        public Integer getSecondsField() {
            return secondsField;
        }

        public void setSecondsField(Integer secondsField) {
            this.secondsField = secondsField;
        }

        public String getPace() {
            return pace;
        }

        public void setPace(String pace) {
            this.pace = pace;
        }

    }