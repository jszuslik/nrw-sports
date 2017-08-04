package com.norulesweb.authapp.core.model.usertypes.coach;

import com.norulesweb.authapp.core.model.usertypes.athlete.Athlete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "COACHES"
)
public class Coach extends Athlete {

    private Set<Athlete> athletes;

    public Coach() { }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "COACH_ATHLETE",
            joinColumns = {@JoinColumn(name = "COACH_ID", referencedColumnName = "ID")},
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
