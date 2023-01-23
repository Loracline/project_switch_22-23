package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountTest {
    /**
     * equals()
     */
    @Test
    void ensureSameAccountEqualsItself() {
        // Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountsAreEqual() {
        // Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account other = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountsAreDifferent() {
        // Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account other = new Account("Mary", "john@isep.ipp.pt", 912345678, null);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountDoesNotEqualsNull() {
        // Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureAccountDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Profile other = new Profile("John");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * hashCode()
     */
    @Test
    void ensureAccountsHaveSameHashCode() {
        // ARRANGE
        Account reference = new Account("John", "john@isep.ipp.pt", 951357852, null);
        Account other = new Account("John", "john@isep.ipp.pt", 951357852, null);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureAccountsHaveDifferentHashCode() {
        // ARRANGE
        Account reference = new Account("John", "john@isep.ipp.pt", 951357852, null);
        Account other = new Account("John", "john@isep.ipp.pt", 951357853, null);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }


    /**
     * GETTER methods
     */
    @Test
    void ensureEmailIsRetrievedSuccessfully() {
        // Arrange
        Account account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        String expected = "john@isep.ipp.pt";

        //ACT
        String result = account.getEmail();

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureGetPhotoRetrievesNonNullValue() throws IOException {
        // ARRANGE
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/old/domainModel_v2_Jan05_2023.png"));
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        accountOne.setPhoto(photo);

        // ACT
        BufferedImage result = accountOne.getPhoto();

        // RESULT
        assertEquals(photo, result);
    }


    /**
     * SETTER methods
     */
    @Test
    void ensureThatPhotoIsSet() throws IOException {
        //ARRANGE
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/old/domainModel_v2_Jan05_2023.png"));
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account accountWithPhoto = new Account("John", "john@isep.ipp.pt", 912345678,
                photo);
        //ACT
        accountOne.setPhoto(photo);
        //ASSERT
        assertEquals(accountWithPhoto, accountOne);
    }

    @Test
    void ensureProfileIsSetSuccessfully() throws IOException {
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/old/domainModel_v2_Jan05_2023.png"));
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, photo); //Default Profile: User
        accountOne.setProfile(new Profile("Manager"));
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, photo);
        assertNotEquals(accountOne, accountTwo);
    }

    @Test
    void ensureAccountProfileIsSetSuccessfully() {
        // Arrange
        Account accountBeforeUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null);//Default Profile: User
        Account accountAfterUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null); //Default Profile: User

        //Create Manager profile
        Profile profileManagerOne = new Profile("Manager");

        //Act
        //Change Profile User (default) to Manager
        accountAfterUpdate.setProfile(profileManagerOne);

        //Assert
        assertNotEquals(accountBeforeUpdate, accountAfterUpdate);
    }

    @Test
    void ensureAccountStatusIsSetToActive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null);

        // Act
        accountOne.setStatus(false);
        accountOne.setStatus(true);

        // Assert
        assertEquals(accountOne, accountTwo);
    }

    @Test
    void ensureAccountStatusIsSetToInactive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null);

        // Act
        accountTwo.setStatus(false);

        // Assert
        assertNotEquals(accountOne, accountTwo);
    }


    /**
     * checkAccountFromEmail(String email)
     */
    @Test
    void checkAccountFromEmailSuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = true;
        //Act
        boolean result = reference.checkAccountFromEmail("john@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void checkAccountFromEmailUnsuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = false;
        //Act
        boolean result = reference.checkAccountFromEmail("antonio@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * isManager()
     */
    @Test
    void verifyIsManagerSuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Profile profile = new Profile("Manager");
        reference.setProfile(profile);
        boolean expected = true;
        //Act
        boolean result = reference.isManager();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void verifyIsManagerUnsuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = false;
        //Act
        boolean result = reference.isManager();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * isAdministrator()
     */
    @Test
    void verifyIsAdministratorSuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Profile profile = new Profile("Administrator");
        reference.setProfile(profile);
        boolean expected = true;
        //Act
        boolean result = reference.isAdministrator();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void verifyIsAdministratorUnsuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = false;
        //Act
        boolean result = reference.isManager();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * isUser()
     */
    @Test
    void verifyIsUserUnsuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Profile profile = new Profile("Manager");
        reference.setProfile(profile);
        boolean expected = false;
        //Act
        boolean result = reference.isUser();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void verifyIsUserSuccessfully() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = true;
        //Act
        boolean result = reference.isUser();
        //Assert
        assertEquals(expected, result);
    }
}