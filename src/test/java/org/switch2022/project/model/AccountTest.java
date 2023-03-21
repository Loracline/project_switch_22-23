package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.factories.FactoryAccount;
import org.switch2022.project.factories.FactoryProfile;
import org.switch2022.project.factories.IFactoryAccount;
import org.switch2022.project.factories.IFactoryProfile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountTest {
    /**
     * Tests for equals() method.
     * Scenario 1: the same account equals itself.
     */
    @Test
    void ensureThatSameAccountEqualsItself() {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: two accounts with the same information are equal.
     */
    @Test
    void ensureThatTwoAccountsAreEqual() {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account other = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: two accounts with different information are not the same.
     */
    @Test
    void ensureThatTwoAccountsAreDifferent() {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account other = factoryAccount.createAccount("Mary", "mary@isep.ipp.pt",
                912345678);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: an account and a null are not the same.
     */
    @Test
    void ensureThatAccountDoesNotEqualsNull() {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: an account and another type of object are not the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatAccountDoesNotEqualOtherTypeOfObject() {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        IFactoryProfile factoryProfile = new FactoryProfile();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Profile other = factoryProfile.createProfile("admin");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests for hashCode() method.
     * Scenario 1: two accounts with the same information have the same hashcode.
     */
    @Test
    void ensureThatAccountsHaveSameHashCode() {
        // ARRANGE
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                951357852);
        Account other = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                951357852);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Scenario 2: two accounts that have different information does not have the same
     * hashcode.
     */
    @Test
    void ensureThatAccountsHaveDifferentHashCode() {
        // ARRANGE
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                951357852);
        Account other = factoryAccount.createAccount("John", "johnny@isep.ipp.pt",
                951357852);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Test if one can retrieve successfully the e-mail from an account.
     */
    @Test
    void ensureThatEmailIsRetrievedSuccessfully() {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account account = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        String expected = "john@isep.ipp.pt";

        //ACT
        String result = account.getEmail();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test if a picture from an account is successfully added. It compares if an
     * account with photo equals another that has a photo added.
     */
    @Test
    void ensureThatPhotoIsSet() throws IOException {
        //ARRANGE
        IFactoryAccount factoryAccount = new FactoryAccount();
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/old" +
                "/domainModel_v2_Jan05_2023.png"));
        Account accountOne = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account accountWithPhoto = factoryAccount.createAccount("John", "john@isep.ipp" +
                        ".pt", 912345678,
                photo);

        //ACT
        accountOne.setPhoto(photo);

        //ASSERT
        assertEquals(accountWithPhoto, accountOne);
    }

    /**
     * Test if a profile is given to an account, by comparing the account's profile
     * before and after the set.
     */
    @Test
    void ensureThatProfileIsSetSuccessfully() throws IOException,
            CloneNotSupportedException {
        //Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/old" +
                "/domainModel_v2_Jan05_2023.png"));
        Account accountOne = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678,
                photo); //Default Profile: User
        Account accountTwo = (Account) accountOne.clone();

        ProfileContainer profileContainer = new ProfileContainer();
        profileContainer.createProfile("manager");

        //Act
        accountOne.setProfile(profileContainer, "Manager");

        //Assert
        assertNotEquals(accountOne.getProfile(), accountTwo.getProfile());
    }

    /**
     * Test if a profile from an account is successfully changed to another. Compares
     * if account with profile and the same without a profile are not the same.
     */
    @Test
    void ensureThatAccountProfileIsSetSuccessfully() throws CloneNotSupportedException {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();

        Account accountBeforeUpdate = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678); //Default Profile: User
        Account accountAfterUpdate = (Account) accountBeforeUpdate.clone();

        ProfileContainer profileContainer = new ProfileContainer();
        profileContainer.createProfile("Manager");

        //Act
        //Change Profile User (default) to Manager
        accountAfterUpdate.setProfile(profileContainer, "Manager");

        //Assert
        assertNotEquals(accountBeforeUpdate.getProfile(), accountAfterUpdate.getProfile());
    }

    /**
     * Test if an account status is set to Active. Should return true, which is
     * equivalent of an inactive profile.
     */
    @Test
    void ensureThatAccountStatusIsSetToActive() throws CloneNotSupportedException {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account accountOne = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account accountTwo = (Account) accountOne.clone();

        // Act
        accountOne.setStatus(false);
        accountOne.setStatus(true);
        boolean statusOne = accountOne.isAccountStatus();
        boolean statusTwo = accountTwo.isAccountStatus();

        // Assert
        assertEquals(statusOne, statusTwo);
    }

    /**
     * Test if an account status is set to Inactive. Should return false, which is
     * equivalent of an inactive profile.
     */
    @Test
    void ensureThatAccountStatusIsSetToInactive() throws CloneNotSupportedException {
        // Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();

        Account accountOne = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        Account accountTwo = (Account) accountOne.clone();

        // Act
        accountTwo.setStatus(false);

        boolean statusOne = accountOne.isAccountStatus();
        boolean statusTwo = accountTwo.isAccountStatus();

        // Assert
        assertNotEquals(statusOne, statusTwo);
    }

    /**
     * Test if an account exists through a giving email. Should return true.
     */
    @Test
    void ensureThatAnAccountExistsThroughAnEmailAddress() {
        //Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();

        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        boolean expected = true;

        //Act
        boolean result = reference.checkAccountFromEmail("john@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test an account exists through a giving email. Should return false.
     */
    @Test
    void ensureThatAccountDoesNotExistsThroughAnEmailAddress() {
        //Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();

        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        boolean expected = false;

        //Act
        boolean result = reference.checkAccountFromEmail("antonio@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This method checks if account's profile is "Manager".
     * Should return true.
     */
    @Test
    void ensureThatAnAccountIsManager() {
        //Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();

        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);

        ProfileContainer profileContainer = new ProfileContainer();
        profileContainer.createProfile("Manager");
        reference.setProfile(profileContainer, "Manager");

        boolean expected = true;

        //Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This method checks if account's profile is not "Manager".
     * Should return false.
     */
    @Test
    void ensureThaAnAccountIsNotManager() {
        //Arrange
        IFactoryAccount factoryAccount = new FactoryAccount();
        Account reference = factoryAccount.createAccount("John", "john@isep.ipp.pt",
                912345678);
        boolean expected = false;

        //Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);

        //Assert
        assertEquals(expected, result);
    }
}