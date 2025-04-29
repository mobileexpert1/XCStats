package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tue {

@SerializedName("hr")
@Expose
private Hr__1 hr;
@SerializedName("1")
@Expose
private _1__1 _1;
@SerializedName("2")
@Expose
private _2__1 _2;

public Hr__1 getHr() {
return hr;
}

public void setHr(Hr__1 hr) {
this.hr = hr;
}

public _1__1 get1() {
return _1;
}

public void set1(_1__1 _1) {
this._1 = _1;
}

public _2__1 get2() {
return _2;
}

public void set2(_2__1 _2) {
this._2 = _2;
}

}