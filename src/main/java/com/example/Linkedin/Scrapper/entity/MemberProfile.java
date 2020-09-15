package com.example.Linkedin.Scrapper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBERPROFILE")
public class MemberProfile extends EntityBase {

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "TITLE", length = 100)
    private String title;

    @Column(name = "PROFILEURL", length = 100)
    private String profileUrl;

    @Column(name = "LOCATION", length = 100)
    private String location;

    public MemberProfile() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
