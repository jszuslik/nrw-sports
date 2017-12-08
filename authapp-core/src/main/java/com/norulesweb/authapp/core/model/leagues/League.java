package com.norulesweb.authapp.core.model.leagues;

import com.norulesweb.authapp.core.model.address.Address;
import com.norulesweb.authapp.core.model.common.ModelBase;
import com.norulesweb.authapp.core.model.teams.Team;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(
    name = "LEAGUES"
)
public class League extends ModelBase {

    private String name;

    private Address address;

    private Instant startDate;

    private Instant endDate;

    private Set<Team> teams;

    private Set<Venue> venues;

    public League() { }

    @Column(name = "LEAGUE_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "LEAGUE_ADDRESS",
            joinColumns = {@JoinColumn(name = "LEAGUE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")}
    )
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "START_DATE")
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    @Column(name = "END_DATE")
    public Instant getEndDate() {
        return endDate;
    }
    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "LEAGUE_TEAMS",
            joinColumns = {@JoinColumn(name = "LEAGUE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")}
    )
    public Set<Team> getTeams() {
        return teams;
    }
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "LEAGUE_VENUES",
            joinColumns = {@JoinColumn(name = "LEAGUE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "VENUE_ID", referencedColumnName = "ID")}
    )
    public Set<Venue> getVenues() {
        return venues;
    }
    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }
}
