package com.norulesweb.authapp.core.model.usertypes.parent;

import com.norulesweb.authapp.core.model.usertypes.athlete.Athlete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ATHLETE_PARENTS")
@EntityListeners(AuditingEntityListener.class)
public class AthleteParent extends Athlete {

    private Set<Athlete> athletes;

    public AthleteParent() { }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PARENT_ATHLETE",
            joinColumns = {@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")},
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
