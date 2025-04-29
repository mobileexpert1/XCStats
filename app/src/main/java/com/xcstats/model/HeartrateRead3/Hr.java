package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hr implements Serializable {

@SerializedName("heartrate")
@Expose
private String heartrate;
@SerializedName("hr_id")
@Expose
private String hrId;
@SerializedName("hr_date")
@Expose
private String hrDate;

public String getHeartrate() {
return heartrate;
}

public void setHeartrate(String heartrate) {
this.heartrate = heartrate;
}

public String getHrId() {
return hrId;
}

public void setHrId(String hrId) {
this.hrId = hrId;
}

public String getHrDate() {
return hrDate;
}

public void setHrDate(String hrDate) {
this.hrDate = hrDate;
}

}