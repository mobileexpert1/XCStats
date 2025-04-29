package com.xcstats.model.plannedabsence;

/**
 * Created by Mobile on 1/16/2017.
 */

public class DayWiseWorkout {

    private Workoutdata dayone = new Workoutdata();

    public Workoutdata getDaytwo() {
        return daytwo;
    }

    public void setDaytwo(Workoutdata daytwo) {
        this.daytwo = daytwo;
    }

    public Workoutdata getDayone() {
        return dayone;
    }

    public void setDayone(Workoutdata dayone) {
        this.dayone = dayone;
    }

    private Workoutdata daytwo = new Workoutdata();

}
