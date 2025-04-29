package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hr__6 implements Serializable {

@SerializedName("heartrate")
@Expose
private Object heartrate;
@SerializedName("hr_id")
@Expose
private String hrId;
@SerializedName("hr_date")
@Expose
private String hrDate;

public Object getHeartrate() {
return heartrate;
}

public void setHeartrate(Object heartrate) {
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