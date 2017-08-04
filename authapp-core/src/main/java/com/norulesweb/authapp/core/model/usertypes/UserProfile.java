package com.norulesweb.authapp.core.model.usertypes;

import com.norulesweb.authapp.core.model.security.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "USER_PROFILE")
@EntityListeners(AuditingEntityListener.class)
public class UserProfile extends User {

    private String profileImageURL;

    private String userType;

    private Instant birthDate;

    private Integer age;

    public UserProfile() { }

    @Column(name = "IMAGE_URL")
    public String getProfileImageURL() {
        return profileImageURL;
    }
    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    @Column(name = "USER_TYPE")
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "BIRTH_DATE")
    public Instant getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "AGE")
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
