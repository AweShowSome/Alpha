package com.alpha.twostudents.alpha;

import android.media.Image;

import java.util.Date;

/**
 * Created by Akshat on 5/23/2018.
 */

public class User {

    public String firstName;
    public String lastName;
    private Date birthDate;
    private Image profilePicture;
    public String emailID;
    private String password;
    private Post posts[];

    /**
     * Constructor for a User object
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param emailID
     * @param password
     */
    public User(String firstName, String lastName, Date birthDate, String emailID, String password){

        this.firstName = firstName;
        this.birthDate = birthDate;
        this.lastName = lastName;
        this.emailID = emailID;
        this.password = password;

    }

    /**
     * Temporary constructor for development use
     * @param firstName
     * @param lastName
     * @param emailID
     */
    public User (String firstName, String lastName, String emailID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
    }

    /**
     * Adding a picture to the User
     * @param profilePicture
     */
    public void addPicture(Image profilePicture){
        this.profilePicture = profilePicture;
    }


    public String print(){
        return firstName + " " + lastName + "\n" + emailID;
    }

}
