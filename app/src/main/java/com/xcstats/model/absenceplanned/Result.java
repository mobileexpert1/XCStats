package com.xcstats.model.absenceplanned;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("id")
@Expose
private String id;
@SerializedName("absent_date")
@Expose
private String absentDate;
@SerializedName("ea_reason")
@Expose
private String eaReason;
@SerializedName("entry_date")
@Expose
private String entryDate;
@SerializedName("delete")
@Expose
private String delete;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getAbsentDate() {
return absentDate;
}

public void setAbsentDate(String absentDate) {
this.absentDate = absentDate;
}

public String getEaReason() {
return eaReason;
}

public void setEaReason(String eaReason) {
this.eaReason = eaReason;
}

public String getEntryDate() {
return entryDate;
}

public void setEntryDate(String entryDate) {
this.entryDate = entryDate;
}

public String getDelete() {
return delete;
}

public void setDelete(String delete) {
this.delete = delete;
}

}


