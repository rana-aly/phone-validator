package com.phonevalidator.dto;

public class PhoneNumberDTO {

    private String name;
    private String phone;
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
