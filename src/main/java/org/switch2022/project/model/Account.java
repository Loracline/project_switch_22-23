package org.switch2022.project.model;

import org.switch2022.project.model.container.ProfileContainer;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The class Account was built to create and manage new accounts.
 * An account is defined by: name, e-mail, phone number, profile, status and
 * photo (the last attribute is optional).
 * The account status is "True" if the account is ACTIVE and is "False" if the
 * account is INACTIVE.
 */
public class Account {
    /**
     * Attributes
     */
    private final String accountName;
    private final String email;
    private final long phoneNumber;
    private Profile profile;
    private boolean accountStatus;
    private BufferedImage photo;


    /**
     * Constructor
     * When an account is instantiated, its default profile is "User", and its
     * default status is ACTIVE (True).
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
     * one, without modifying the existing object.
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
        if (toCompare == null) {
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
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
    public boolean isAccountStatus() {
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
     * Setter method for the attribute: profile.
     *
     */
    public void setProfile(ProfileContainer profileContainer, String profileName) {
        Profile profile = profileContainer.getProfileByName(profileName);
        if (profile != null) {
            this.profile = profile;
        }
    }

    /**
     * Setter method for the attribute: photo.
     *
     * @param photo of the account.
     */
    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
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
     * This method checks if account's profile is the profile required.
     *
     * @return TRUE if it is the profile required and FALSE otherwise.
     */

    public boolean isProfileRequired(String profileNameRequired) {
        return this.profile.isProfileRequired(profileNameRequired);
    }
}