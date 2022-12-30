package org.switch2022.project;
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
     */
    private String name;
    private String email;
    private long phoneNumber;
    private BufferedImage photo;
    private boolean accountStatus;
    private Profile accountProfile;


    /**
     * Constructor of the class Account.
     * New instance is created using as parameter the essential attributes.
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     *
     */
    public Account(String name, String email, long phoneNumber,BufferedImage photo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountProfile = new Profile("User");

        if(photo != null){
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
                accountStatus == account.accountStatus &&
                name.equals(account.name) && email.equals(account.email) &&
                Objects.equals(photo, account.photo) &&
                accountProfile.equals(account.accountProfile);
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


    /**
     * Getter method for the attribute ACCOUNT STATUS.
     *
     * @return TRUE if the account status is "Active" and FALSE if the account
     * status is "Inactive"
     */
    public boolean isAccountStatusActive() {
        return accountStatus;
    }


    /**
     * Setter method for the attribute ACCOUNT STATUS.
     *
     * @param accountStatus TRUE = "ACTIVE" or FALSE = "INACTIVE"
     */
    public void updateAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Setter method for the attribute PROFILE.
     *
     * @param profile of the instance of Account
     */
    public void updateAccountProfile(Profile profile) {
        this.accountProfile = profile;
    }
}