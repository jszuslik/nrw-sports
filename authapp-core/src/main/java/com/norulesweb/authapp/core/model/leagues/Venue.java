package com.norulesweb.authapp.core.model.leagues;

import com.norulesweb.authapp.core.model.address.Address;
import com.norulesweb.authapp.core.model.common.ModelBase;

import javax.persistence.*;

@Entity
@Table(
    name = "VENUES"
)
public class Venue extends ModelBase {

    private String name;

    private String phone;

    private String email;

    private Address address;

    public Venue() { }

    @Column(name = "VENUE_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "VENUE_ADDRESS",
            joinColumns = {@JoinColumn(name = "VENUE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")}
    )
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
