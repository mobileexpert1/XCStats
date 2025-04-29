package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sun {

@SerializedName("hr")
@Expose
private Hr__6 hr;
@SerializedName("1")
@Expose
private _1__6 _1;
@SerializedName("2")
@Expose
private _2__6 _2;

public Hr__6 getHr() {
return hr;
}

public void setHr(Hr__6 hr) {
this.hr = hr;
}

public _1__6 get1() {
return _1;
}

public void set1(_1__6 _1) {
this._1 = _1;
}

public _2__6 get2() {
return _2;
}

public void set2(_2__6 _2) {
this._2 = _2;
}

}