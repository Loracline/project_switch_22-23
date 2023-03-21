package org.switch2022.project.model;

import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.factories.FactoryProfile;
import org.switch2022.project.factories.IFactoryProfile;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The class Account was built to create and manage new accounts.
 * An account is defined by: name, e-mail, phone number, profile, status and
 * photo (this last attribute is optional).
 * The account status is "True" if the account is ACTIVE and is "False" if the
 * account is INACTIVE.
 */
public class Account implements Cloneable {
    /**
     * Attributes
     */
    private final String accountName;
    private final String email;
    private final long phoneNumber;
    IFactoryProfile factoryProfile;
    private Profile profile;
    private boolean accountStatus;
    private BufferedImage photo;

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface.
     *                                    Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an
     *                                    instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Constructor.
     *
     * @param builder used to create an Account.
     */
    private Account(AccountBuilder builder) {
        this.email = builder.email;
        this.accountName = builder.accountName;
        this.phoneNumber = builder.phoneNumber;
        this.photo = builder.photo;
        this.factoryProfile = new FactoryProfile();
        this.profile = factoryProfile.createProfile("user");
        this.accountStatus = true;
    }



    // check
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

    // check
    public Account(Account other) {
        this.accountName = other.accountName;
        this.email = other.email;
        this.phoneNumber = other.phoneNumber;
        this.profile = other.profile;
        this.accountStatus = other.accountStatus;
        this.photo = other.photo;
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



    // check
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
     */
    public void setProfile(ProfileContainer profileContainer, String profileName) {
        Profile newProfile = profileContainer.getProfileByName(profileName);
        if (newProfile != null) {
            this.profile = newProfile;
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

    /**
     * This class applies the Builder Pattern to create a new Account.
     */
    public static class AccountBuilder {
        private final String email;
        private String accountName;
        private long phoneNumber;
        private BufferedImage photo;

        /**
         * This method creates an Account with its identifier attribute.
         *
         * @param email of the Account to create.
         */
        public AccountBuilder(String email) {
            this.email = email.toLowerCase();
        }

        /**
         * This method sets the name of the Account.
         *
         * @param accountName of the Account to create.
         * @return the Account with a name.
         */
        public AccountBuilder setAccountName(String accountName) {
            this.accountName = accountName.toLowerCase();
            return this;
        }

        /**
         * This method sets the phone number of the Account.
         *
         * @param phoneNumber of the Account to create.
         * @return the Account with a phone number.
         */
        public AccountBuilder setPhoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * This method sets the photo of the Account.
         *
         * @param photo of the Account to create.
         * @return the Account with a photo.
         */
        public AccountBuilder setPhoto(BufferedImage photo) {
            this.photo = photo;
            return this;
        }

        /**
         * This method creates an Account with the chosen attributes.
         *
         * @return an Account with the chosen attributes.
         */
        public Account build() {
            return new Account(this);
        }
    }
}