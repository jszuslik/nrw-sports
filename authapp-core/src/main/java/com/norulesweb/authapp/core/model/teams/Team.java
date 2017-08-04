package com.norulesweb.authapp.core.model.teams;

import com.norulesweb.authapp.core.model.common.ModelBase;
import com.norulesweb.authapp.core.model.usertypes.athlete.Athlete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "TEAM"
)
public class Team extends ModelBase {

    private String name;

    private Set<Athlete> athletes;

    public Team() { }

    @Column(name = "TEAM_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TEAM_ATHLETE",
            joinColumns = {@JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ATHLETE_ID", referencedColumnName = "ID")}
    )
    public Set<Athlete> getAthletes() {
        return athletes;
    }
    public void setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
    }
    public void addAthlete(Athlete athlete) {
        if(this.athletes == null) {
            this.athletes = new HashSet<>();
        }
        this.athletes.add(athlete);
    }

}
