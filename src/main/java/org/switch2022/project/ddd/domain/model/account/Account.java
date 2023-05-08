package org.switch2022.project.ddd.domain.model.account;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.PhoneNumber;
import org.switch2022.project.ddd.domain.value_object.Photo;

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
    private boolean accountStatus;
    private Photo photo;

    protected Account(Name name, Email email, PhoneNumber phoneNumber, Photo photo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountStatus = true;

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
     * @return account name.
     */
    public String getAccountName() {
        return name.getName();
    }

    /**
     * Getter method that returns a String with the Account email
     * @return account email.
     */

    public String getAccountEmail() {
        return email.getEmail();
    }

    /**
     * Getter method that returns a boolean with the Account status
     * @return account status.
     */
    public boolean getAccountStatus() {
        return accountStatus;
    }

    /**
     * Method change the status of the account.
     * @param status to be used to change.
     * @return the updated status.
     */
    public boolean changeStatus(boolean status) {
        this.accountStatus = status;
        return getAccountStatus();
    }
}
