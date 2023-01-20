package org.switch2022.project.model;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Class Account is built to create and manage new accounts.
 * An account is defined by a name, an email, a phone number, a profile, an
 * account status and a photo (optional).
 */
public class Account {
    /**
     * Attributes of the class Account, according to the Class Diagram.
     *
     * @param status is true (active) or false (inactive)
     */
    private String name;
    private String email;
    private long phoneNumber;
    private BufferedImage photo;
    private boolean status;
    private Profile profile;

    /**
     * Constructor of the class Account.
     * New instance is created using as parameter the essential attributes.
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     */
    public Account(String name, String email, long phoneNumber, BufferedImage photo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profile = new Profile("User");
        this.status = true;

        if (photo != null) {
            this.photo = photo;
        }
    }

    /**
     * Method that checks if two instances of Account are equal by comparing
     * their e-mail addresses.
     *
     * @param toCompare Account instance to compare with
     * @return TRUE if the two have the same e-mail address, and FALSE otherwise
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) return true;
        if (!(toCompare instanceof Account)) return false;
        Account account = (Account) toCompare;
        return phoneNumber == account.phoneNumber &&
                status == account.status &&
                name.equals(account.name) && email.equals(account.email) &&
                Objects.equals(photo, account.photo) &&
                profile.equals(account.profile);
    }

    /**
     * Setter method for the attribute PHOTO.
     *
     * @param photo of the instance of Account
     */
    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }


    /**
     * Getter method for the attribute E-MAIL.
     *
     * @return the email of the account
     */
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public BufferedImage getPhoto() {
        return photo;
    }

    public boolean getStatus() {
        return status;
    }

    public Profile getProfile() {
        return profile;
    }

    /**
     * Setter method for the attribute ACCOUNT STATUS.
     *
     * @param status TRUE = "ACTIVE" or FALSE = "INACTIVE"
     */
    public boolean setStatus(boolean status) {
        return this.status = status;
    }

    /**
     * Setter method for the attribute PROFILE.
     *
     * @param profile of the instance of Account
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean checkAccountFromEmail(String email) {
        boolean account = false;
        if (this.email.equals(email)) {
            account = true;
        }
        return account;
    }

    public boolean IsManager() {
        boolean isManager = false;
        if (this.profile.isManager()){
            isManager=true;
        }
        return isManager;
    }
    public boolean IsAdministrator() {
        boolean isAdministrator = false;
        if(this.profile.isAdministrator()){
            isAdministrator=true;
        }
        return isAdministrator;
    }

    public boolean IsUser() {
        boolean isUser = false;
        if (this.profile.isUser()){
            isUser=true;
        }
        return isUser;
    }

}