package org.switch2022.project.ddd.domain.model.account;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The class Account was built to create and manage new accounts.
 * An account is defined by: name, e-mail, phone number, profile, status and
 * photo (this last attribute is optional).
 * The account status is "True" if the account is ACTIVE and is "False" if the
 * account is INACTIVE.
 */

public class Account implements Entity<Account> {

    /**
     * Attributes
     */
    private final Name name;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private AccountStatus accountStatus;
    private Photo photo;
    private ProfileId profileId;


    protected Account(Name name, Email email, PhoneNumber phoneNumber, Photo photo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountStatus = AccountStatus.ACTIVE;
        this.profileId = new ProfileId(1);

        if (photo != null) {
            this.photo = photo;
        }
    }

    /**
     * This method checks if two instances of Account are equal by comparing all their
     * attributes.
     *
     * @param toCompare Account instance to compare with.
     * @return TRUE if the two have the same attributes, and FALSE otherwise.
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
        return Objects.equals(email, account.email);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an object,
     * based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * This method checks if two instances of Account are equal by comparing the
     * value of the attribute email.
     *
     * @param other Account instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE
     * otherwise.
     */
    @Override
    public boolean sameIdentityAs(Account other) {
        return this.email.equals(other.email);
    }

    /**
     * Getter method that returns a String with the Account name
     *
     * @return account name.
     */
    public String getAccountName() {
        return name.getName();
    }

    /**
     * Getter method that returns a String with the Account email
     *
     * @return account email.
     */

    public String getAccountEmail() {
        return email.getEmailAddress();
    }

    /**
     * Getter method that returns a boolean with the Account status
     *
     * @return account status.
     */
    public String getAccountStatus() {
        return accountStatus.getAccountStatus();
    }

    /**
     * Getter method that returns a String with the profileId
     *
     * @return profileId.
     */
    public String getProfileId() {
        return profileId.getProfileId();
    }

    /**
     * Getter method that returns an int with the phoneNumber
     *
     * @return phoneNumber.
     */
    public int getPhoneNumber() {
        return phoneNumber.getPhoneNumber();
    }

    /**
     * Getter method that returns a BufferedImage with the photo
     *
     * @return photo.
     */
    public BufferedImage getPhoto() {
        BufferedImage bufferedImage = null;
        if (photo != null) {
            bufferedImage = photo.getPicture();
        }
        return bufferedImage;
    }

    /**
     * Method change the status of the account.
     *
     * @param status to be used to change.
     * @return the updated status.
     */
    public boolean changeStatus(AccountStatus status) {
        this.accountStatus = status;
        return true;
    }

    /**
     * This method checks if the account has the same email as a given one.
     *
     * @param email to check is the account has a match
     * @return true if account has the given email and false otherwise.
     */
    public boolean hasEmail(String email) {
        return this.email.getEmailAddress().equals(email);
    }

    /**
     * This method checks if the account has the same accountStatus as a given one.
     *
     * @return true if account has the given accountStatus and false otherwise.
     */
    public boolean isAccountActive() {
        return this.accountStatus.sameValueAs(AccountStatus.ACTIVE);
    }

    /**
     * Checks and updates the profile.
     *
     * @param profileId the profile to be set
     * @return true if the profile was updated, false if the profile remains the same.
     */
    public boolean changeProfile(ProfileId profileId) {
        if (this.profileId == null || !this.profileId.sameValueAs(profileId)) {
            this.profileId = profileId;
            return true;
        } else {
            return false;
        }
    }
}
