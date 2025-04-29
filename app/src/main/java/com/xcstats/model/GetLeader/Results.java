package com.xcstats.model.GetLeader;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("heading")
@Expose
private String heading;
@SerializedName("have_results")
@Expose
private Boolean haveResults;
@SerializedName("msg")
@Expose
private String msg;
@SerializedName("rows")
@Expose
private List<Row> rows = null;

public String getHeading() {
return heading;
}

public void setHeading(String heading) {
this.heading = heading;
}

public Boolean getHaveResults() {
return haveResults;
}

public void setHaveResults(Boolean haveResults) {
this.haveResults = haveResults;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<Row> getRows() {
return rows;
}

public void setRows(List<Row> rows) {
this.rows = rows;
}

}