package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fri {

@SerializedName("hr")
@Expose
private Hr__4 hr;
@SerializedName("1")
@Expose
private _1__4 _1;
@SerializedName("2")
@Expose
private _2__4 _2;

public Hr__4 getHr() {
return hr;
}

public void setHr(Hr__4 hr) {
this.hr = hr;
}

public _1__4 get1() {
return _1;
}

public void set1(_1__4 _1) {
this._1 = _1;
}

public _2__4 get2() {
return _2;
}

public void set2(_2__4 _2) {
this._2 = _2;
}

}