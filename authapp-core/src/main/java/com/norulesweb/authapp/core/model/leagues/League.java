package com.norulesweb.authapp.core.model.leagues;

import com.norulesweb.authapp.core.model.common.ModelBase;
import com.norulesweb.authapp.core.model.teams.Team;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(
    name = "LEAGUES"
)
public class League extends ModelBase {

    private String name;

    private String zipCode;

    private Instant startDate;

    private Instant endDate;

    private Set<Team> teams;

    private Set<Venue> venues;

    public League() { }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }
    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Set<Team> getTeams() {
        return teams;
    }
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Venue> getVenues() {
        return venues;
    }
    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }
}
