package com.norulesweb.authapp.core.model.address;


import com.norulesweb.authapp.core.model.common.ModelBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
    name = "ADDRESS"
)
public class Address extends ModelBase {

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String postal;

    private String country;

    public Address() { }

    @Column(name = "ADDRESS_1")
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(name = "ADDRESS_2")
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "POSTAL_CODE")
    public String getPostal() {
        return postal;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
