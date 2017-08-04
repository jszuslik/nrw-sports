package com.norulesweb.authapp.core.model.usertypes.athlete;

import com.norulesweb.authapp.core.model.usertypes.UserProfile;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "ATHLETE_PARENTS")
@EntityListeners(AuditingEntityListener.class)
public class Athlete extends UserProfile {

    public Athlete() { }

}
