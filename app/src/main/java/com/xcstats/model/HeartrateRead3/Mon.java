package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mon {

@SerializedName("hr")
@Expose
private Hr hr;
@SerializedName("1")
@Expose
private _1 _1;
@SerializedName("2")
@Expose
private _2 _2;
public Hr getHr() {
return hr;
}

public void setHr(Hr hr) {
this.hr = hr;
}

public _1 get1() {
return _1;
}

public void set1(_1 _1) {
this._1 = _1;
}

public _2 get2() {
return _2;
}

public void set2(_2 _2) {
this._2 = _2;
}

}