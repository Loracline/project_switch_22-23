package org.switch2022.project.model;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Class Account is built to create and manage new accounts.
 * An account is defined by: name, email, phone number, profile, status and photo
 * (optional).
 * Status is "True" if ACTIVE and "False" if INACTIVE.
 */
public class Account {
    /**
     * Attributes
     */
    private final String accountName;
    private final String email;
    private final long phoneNumber;
    private Profile profile;
    private BufferedImage photo;
    private boolean accountStatus;


    /**
     * Constructor
     */
    public Account(String name, String email, long phoneNumber, BufferedImage photo) {
        this.accountName = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profile = new Profile("User");
        this.accountStatus = true;

        if (photo != null) {
            this.photo = photo;
        }
    }

    /**
     * Copy Constructor
     * Provides a way to create a new object with the same state as an existing
     * object, without modifying the existing object.
     */
    public Account(Account other) {
        this.accountName = other.accountName;
        this.email = other.email;
        this.phoneNumber = other.phoneNumber;
        this.profile = other.profile;
        this.accountStatus = other.accountStatus;
        this.photo = other.photo;
    }


    /**
     * This method checks if two instances of Account are equal by comparing
     * all their attributes.
     *
     * @param toCompare Account instance to compare with
     * @return TRUE if the two have the same attributes, and FALSE otherwise
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (!(toCompare instanceof Account)) {
            return false;
        }
        Account account = (Account) toCompare;
        return phoneNumber == account.phoneNumber &&
                accountStatus == account.accountStatus &&
                accountName.equals(account.accountName) && email.equals(account.email) &&
                Objects.equals(photo, account.photo) &&
                profile.equals(account.profile);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountName, email, phoneNumber, profile, photo, accountStatus);
    }


    // "GETTERS"

    /**
     * Getter method for the attribute: e-mail.
     *
     * @return e-mail of the account.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter method for the attribute: name.
     *
     * @return name of the account.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Getter method for the attribute: status.
     *
     * @return status of the account.
     */
    public boolean getAccountStatus() {
        return accountStatus;
    }

    /**
     * Getter method for the attribute: profile.
     *
     * @return profile of the account.
     */
    public Profile getProfile() {
        return profile;
    }


    // "SETTERS"

    /**
     * Setter method for the attribute: photo.
     *
     * @param photo of the account.
     */
    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    /**
     * Setter method for the attribute: profile.
     *
     * @param profile of the account.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Setter method for the attribute: status.
     *
     * @param status TRUE = "ACTIVE" or FALSE = "INACTIVE"
     */
    public void setStatus(boolean status) {
        this.accountStatus = status;
    }


    // VALIDATION

    /**
     * This method verifies if account is the one intended through its e-mail.
     *
     * @param email of the seeked account
     * @return TRUE if account has given e-mail, and FALSE otherwise.
     */
    public boolean checkAccountFromEmail(String email) {
        return this.email.equals(email);
    }


    /**
     * This method checks if account's profile is "Manager".
     *
     * @return TRUE if "Manager" and FALSE otherwise.
     */
    public boolean isManager() {
        return this.profile.isManager();
    }

    /**
     * This method checks if account's profile is "Administrator".
     *
     * @return TRUE if "Administrator" and FALSE otherwise.
     */
    public boolean isAdministrator() {
        return this.profile.isAdministrator();
    }

    /**
     * This method checks if account's profile is "User".
     *
     * @return TRUE if "User" and FALSE otherwise.
     */
    public boolean isUser() {
        return this.profile.isUser();
    }
}