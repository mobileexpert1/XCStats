package com.xcstats.model.leadboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("display_form")
@Expose
private Boolean displayForm;
@SerializedName("msg")
@Expose
private String msg;
@SerializedName("radios")
@Expose
private List<Radio> radios = null;
@SerializedName("gradeSelect")
@Expose
private GradeSelect gradeSelect;
@SerializedName("genderSelect")
@Expose
private GenderSelect genderSelect;

public Boolean getDisplayForm() {
return displayForm;
}

public void setDisplayForm(Boolean displayForm) {
this.displayForm = displayForm;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<Radio> getRadios() {
return radios;
}

public void setRadios(List<Radio> radios) {
this.radios = radios;
}

public GradeSelect getGradeSelect() {
return gradeSelect;
}

public void setGradeSelect(GradeSelect gradeSelect) {
this.gradeSelect = gradeSelect;
}

public GenderSelect getGenderSelect() {
return genderSelect;
}

public void setGenderSelect(GenderSelect genderSelect) {
this.genderSelect = genderSelect;
}

}