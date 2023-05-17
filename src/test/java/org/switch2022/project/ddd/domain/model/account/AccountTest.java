package org.switch2022.project.ddd.domain.model.account;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.model.profile.ProfileFactory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.infrastructure.ProfileRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountTest {

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameAccountEqualsItself() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same email are equal.
     */
    @Test
    void ensureTwoInstancesWithSameEmailAreEqual() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Name nameDoubleTwo = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumber phoneNumberDoubleTwo = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Photo photoDoubleTwo = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account other = new Account(nameDoubleTwo, emailDouble, phoneNumberDoubleTwo, photoDoubleTwo);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify that two objects with different email are not equal.
     */
    @Test
    void ensureTwoDifferentPhotoInstancesAreNotTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Name nameDoubleTwo = mock(Name.class);
        Email emailDouble = mock(Email.class);
        Email emailDoubleTwo = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumber phoneNumberDoubleTwo = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Photo photoDoubleTwo = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account other = new Account(nameDoubleTwo, emailDoubleTwo, phoneNumberDoubleTwo, photoDoubleTwo);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if an Account instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("All")
    @Test
    void ensureAccountDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Object other = new Object();

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if an Account and a null object are not the same.
     */
    @Test
    void ensureEmailInstanceDoesNotEqualNull() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Account objects are the same.
     */
    @Test
    public void ensureTwoAccountInstancesHashcodeAreTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Account accountOne = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account accountTwo = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);

        // Act
        int accountOneHashCode = accountOne.hashCode();
        int accountTwoHashCode = accountTwo.hashCode();

        // Assert
        assertEquals(accountOneHashCode, accountTwoHashCode);
    }

    /**
     * Scenario 2: Two different Account objects are not the same.
     */
    @Test
    public void ensureTwoAccountInstancesHashcodeAreNotTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDoubleOne = mock(Email.class);
        Email emailDoubleTwo = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Account accountOne = new Account(nameDouble, emailDoubleOne, phoneNumberDouble, photoDouble);
        Account accountTwo = new Account(nameDouble, emailDoubleTwo, phoneNumberDouble, photoDouble);

        // Act
        int accountOneHashCode = accountOne.hashCode();
        int accountTwoHashCode = accountTwo.hashCode();

        // Assert
        assertNotEquals(accountOneHashCode, accountTwoHashCode);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Check if two instances of Account are equal if the value of their
     * email are the same.
     */
    @Test
    void ensureThatTwoAccountsAreTheSame() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Account account = new Account(name, email, phoneNumber, photo);
        Account accountTwo = new Account(name, email, phoneNumber, photo);

        // Act
        boolean result = account.sameIdentityAs(accountTwo);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Account are not equal if the value of
     * email are not the same.
     */
    @Test
    void ensureThatTwoAccountsAreNotTheSame() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Email emailTwo = mock(Email.class);

        Account account = new Account(name, email, phoneNumber, photo);
        Account accountTwo = new Account(name, emailTwo, phoneNumber, photo);

        // Act
        boolean result = account.sameIdentityAs(accountTwo);

        //Assert
        assertFalse(result);
    }
    /**
     * METHOD getAccountName()
     * <br>
     * Scenario 1: should return the name of the Account.
     */
    @Test
    void getAccountNameReturnsCorrectName() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        Account account = new Account(name, email, phoneNumber, photo);
        when(name.getName()).thenReturn("john doe");

        // Act
        String accountName = account.getAccountName();

        // Assert
        assertEquals("john doe", accountName);
    }
    /**
     * METHOD getAccountEmail()
     * <br>
     * Scenario 1: should return the email of the Account.
     */
    @Test
    void getAccountEmailReturnsCorrectEmail() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        Account account = new Account(name, email, phoneNumber, photo);
        when(email.getEmail()).thenReturn("johndoe@example.com");

        // Act
        String accountEmail = account.getAccountEmail();

        // Assert
        assertEquals("johndoe@example.com", accountEmail);
    }
    /**
     * METHOD getAccountStatus()
     * <br>
     * Scenario 1: should return the status of the Account.
     */
    @Test
    void getAccountStatusReturnsCorrectStatus() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        Account account = new Account(name, email, phoneNumber, photo);
        String expected = "active";
        // Act
        String accountStatus = account.getAccountStatus();

        // Assert
        assertEquals(expected, accountStatus);
    }

    /**
     * Method changeStatus()
     * Scenario 1: changes the status to false, which means to inactivate an account.
     * Should return the status as FALSE
     */
    @Test
    void ensureThatAccountIsInactivated() {
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        String expected = "inactive";
        Account account = new Account(name, email, phoneNumber, photo);
        boolean result = account.changeStatus(AccountStatus.INACTIVE);

        assertTrue(result);
        assertEquals(expected, account.getAccountStatus());
    }

    /**
     * Scenario 2: changes the status to true, which means to activate an account.
     * Should return the status as FALSE
     */
    @Test
    void ensureThatAccountIsActivated() {
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        String expected = "active";
        Account account = new Account(name, email, phoneNumber, photo);
        account.changeStatus(AccountStatus.INACTIVE);

        boolean result = account.changeStatus(AccountStatus.ACTIVE);

        assertTrue(result);
        assertEquals(expected, account.getAccountStatus());
    }

    /**
     * Scenario 3: status does not change, as it has the same value as the parameter.
     */

    @Test
    void ensureThatAccountIsTheSameAsBefore() {
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        String expected = "active";
        Account account = new Account(name, email, phoneNumber, photo);
        boolean result = account.changeStatus(AccountStatus.ACTIVE);

        assertTrue(result);
        assertEquals(expected , account.getAccountStatus());
    }

    /**
     * Method hasEmail()
     * Scenario 1: account has the given email, should return TRUE.
     */
    @Test
    void ensureThatAccountHasTheGivenEmail() {
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Account account = new Account(name, email, phoneNumber, photo);
        when(email.getEmail()).thenReturn("johndoe@example.com");

        boolean result = account.hasEmail("johndoe@example.com");

        assertTrue(result);
    }

    /**
     * Scenario 2: account does not have the given email, should return FALSE.
     */
    @Test
    void ensureThatAccountDoesNotHaveTheGivenEmail() {
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Account account = new Account(name, email, phoneNumber, photo);
        when(email.getEmail()).thenReturn("johndoe@example.com");
        boolean result = account.hasEmail("jamese@example.com");

        assertFalse(result);
    }

    /**
     * Method isAccountActive().
     * Scenario 01:  account is activate.
     */
    @Test
    void ensureThatAccountIsActivate() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account account = new Account(name, email, phoneNumber, photo);
        when(accountStatus.getAccountStatus()).thenReturn(AccountStatus.ACTIVE.getAccountStatus());

        // Act
        boolean result = account.isAccountActive(accountStatus.getAccountStatus());

        // Assert
        assertTrue(result);
    }
    /**
     * Scenario 02:  account is inactive.
     */
    @Test
    void ensureThatAccountIsInactive() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account account = new Account(name, email, phoneNumber, photo);
        when(accountStatus.getAccountStatus()).thenReturn(AccountStatus.INACTIVE.getAccountStatus());

        // Act
        boolean result = account.isAccountActive(accountStatus.getAccountStatus());

        // Assert
        assertFalse(result);
    }

    /**
     * Method setProfile()
     * Scenario 1: Tests the successful change of a profile in an account.
     * Compares if the account with a profile and
     * the same account without a profile are not equal.
     */

    @Test
    void ensureThatAccountProfileIsSetSuccessfully() {
        // Arrange
        ProfileFactory profileFactory = new ProfileFactory();
        ProfileRepository profileRepository = mock(ProfileRepository.class);
        Name profileName = new Name("Manager");
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Account account = new Account(name, email, phoneNumber, photo);

        when(profileRepository.getProfileByName(profileName)).thenReturn(profileFactory.createProfile(name,1));

        // Act
        //Change Profile User (default) to Manager
        boolean result = account.setProfile(profileFactory.createProfile(name, 1));

        // Assert
        assertTrue(result);
        assertEquals(profileFactory.createProfile(name, 1), account.getProfile());
    }


    /**
     * Scenario 2: Tests the unsuccessful addition of a profile in an account. Tests that the account
     * cannot have more than one profile associated with it.
     */
    @Test
    void ensureThatAccountCanHaveOnlyOneProfile() {
        // Arrange
        ProfileFactory profileFactory = new ProfileFactory();
        Name profileName = new Name("User");
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Account account = new Account(name, email, phoneNumber, photo);
        Profile profile1 = profileFactory.createProfile(profileName, 1);
        account.setProfile(profile1);

        // Create another profile
        Profile profile2 = profileFactory.createProfile(new Name("Another User"), 2);

        // Act
        boolean result = account.setProfile(profile2);
        Profile actualProfile = account.getProfile();
        Profile expectedProfile = profile1;

        // Assert
        assertFalse(result);
        assertEquals(expectedProfile.getProfileName(), actualProfile.getProfileName());
    }

    /**
     * METHOD getAccountProfile()
     * <br>
     * Scenario 1: should return the profile of the Account.
     */
    @Test
    void getAccountProfileReturnsCorrectProfile() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        Account account = new Account(name, email, phoneNumber, photo);
        Profile profile = mock(Profile.class);
        account.setProfile(profile);

        // Act
        Profile accountProfile = account.getProfile();

        // Assert
        assertEquals(profile, accountProfile);
    }
}



