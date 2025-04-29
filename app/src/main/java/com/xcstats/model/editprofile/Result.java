package com.xcstats.model.editprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

@SerializedName("email")
@Expose
private String email;
@SerializedName("password")
@Expose
private String password;
@SerializedName("street")
@Expose
private String street;
@SerializedName("city")
@Expose
private String city;
@SerializedName("state")
@Expose
private String state;
@SerializedName("zip")
@Expose
private String zip;
@SerializedName("home_phone")
@Expose
private String homePhone;
@SerializedName("cell_phone")
@Expose
private String cellPhone;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("last_name")
@Expose
private String lastName;
    @SerializedName("cellProvider")
    @Expose
    private List<CellProvider> cellProvider = null;

    public List<CellProvider> getCellProvider() {
        return cellProvider;
    }

    public void setCellProvider(List<CellProvider> cellProvider) {
        this.cellProvider = cellProvider;
    }

    public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getStreet() {
return street;
}

public void setStreet(String street) {
this.street = street;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getZip() {
return zip;
}

public void setZip(String zip) {
this.zip = zip;
}

public String getHomePhone() {
return homePhone;
}

public void setHomePhone(String homePhone) {
this.homePhone = homePhone;
}

public String getCellPhone() {
return cellPhone;
}

public void setCellPhone(String cellPhone) {
this.cellPhone = cellPhone;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

}