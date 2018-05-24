package com.alpha.twostudents.alpha;

import android.media.Image;

import java.util.Date;

/**
 * Created by Akshat on 5/23/2018.
 */

public class User {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Image profilePicture;
    private String emailID;
    private String password;
    private Post posts[];

    public User(String firstName, String lastName, Date birthDate, String emailID, String password){

        this.firstName = firstName;
        this.birthDate = birthDate;
        this.lastName = lastName;
        this.emailID = emailID;
        this.password = password;

    }

    public void addPicture(Image profilePicture){
        this.profilePicture = profilePicture;
    }


}
