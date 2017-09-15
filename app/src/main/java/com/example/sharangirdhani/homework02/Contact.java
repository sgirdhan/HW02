package com.example.sharangirdhani.homework02;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sharangirdhani on 9/15/17.
 */

public class Contact implements Serializable{

    // Instance variables
    public String firstName;
    public String lastName;
    public String company;
    public String email;
    public String phone;
    public String url;
    public String address;
    public Date birthdate;
    public String nickname;
    public String facebookUrl;
    public String twitterUrl;
    public String skype;
    public String youtube;
    public String profilePic;

    // Constructor
    public Contact(String firstName, String lastName, String company, String email, String phone, String url, String address, Date birthdate, String nickname, String facebookUrl, String twitterUrl, String skype, String youtube, String profilePic) {
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
        this.profilePic = profilePic;
    }

}
