package com.example.sharangirdhani.homework02;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sharangirdhani on 9/15/17.
 */

public class Contact implements Serializable {

    // Instance variables
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String phone;
    private String url;
    private String address;
    public Date birthdate;
    private String nickname;
    private String facebookUrl;
    private String twitterUrl;
    private String skype;
    private String youtube;
    private String avatar;

    // Constructor
    public Contact(String firstName, String lastName, String company, String email,
                   String phone, String url, String address, Date birthdate,
                   String nickname, String facebookUrl, String twitterUrl,
                   String skype, String youtube,  String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.url = url;
        this.address = address;
        this.birthdate = birthdate;
        this.nickname = nickname;
        this.facebookUrl = facebookUrl;
        this.twitterUrl = twitterUrl;
        this.skype = skype;
        this.youtube = youtube;
        this.avatar = avatar;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }

    public String getAddress() {
        return address;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public String getSkype() {
        return skype;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}