package com.xcstats.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("coach_name")
@Expose
private String coachName;
@SerializedName("email")
@Expose
private String email;

public String getCoachName() {
return coachName;
}

public void setCoachName(String coachName) {
this.coachName = coachName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}