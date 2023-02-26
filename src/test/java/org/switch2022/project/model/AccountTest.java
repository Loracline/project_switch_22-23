package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.container.ProfileContainer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountTest {
    /**
     * Tests for equals() method.
     */
    @Test
    void ensureThatSameAccountEqualsItself() {
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
    void ensureThatTwoAccountsAreEqual() {
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
    void ensureThatTwoAccountsAreDifferent() {
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
    void ensureThatAccountDoesNotEqualsNull() {
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
    void ensureThatAccountDoesNotEqualOtherTypeOfObject() {
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
     * Tests for hashCode() method.
     */
    @Test
    void ensureThatAccountsHaveSameHashCode() {
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
    void ensureThatAccountsHaveDifferentHashCode() {
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
     * Test if an email from an account is successfully retrieved. It compares the expected email from what it got.
     * Should return true.
     */
    @Test
    void ensureThatEmailIsRetrievedSuccessfully() {
        // Arrange
        Account account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        String expected = "john@isep.ipp.pt";

        //ACT
        String result = account.getEmail();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test if a picture from an account is successfully added. It compares if an account with photo equals another
     * that has a photo added to it and should return true.
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

    /**
     * Test if a profile is given to an account. Compares if account with profile and the same without a profile are
     * not the same. Should return false.
     */

    @Test
    void ensureThatProfileIsSetSuccessfully() throws IOException {
        //Arrange
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/old/domainModel_v2_Jan05_2023.png"));
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, photo); //Default Profile: User
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, photo);
        ProfileContainer profileContainer = new ProfileContainer();
        profileContainer.createProfile("Manager");

        //Act

        accountOne.setProfile(profileContainer, "Manager");

        //Assert
        assertNotEquals(accountOne, accountTwo);
    }

    /**
     * Test if a profile from an account is successfully changed to another. Compares if account with profile and the same without a profile are
     * not the same. Should return false.
     */

    @Test
    void ensureThatAccountProfileIsSetSuccessfully() {
        // Arrange
        Account accountBeforeUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null);//Default Profile: User
        Account accountAfterUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null); //Default Profile: User
        ProfileContainer profileContainer = new ProfileContainer();
        profileContainer.createProfile("Manager");

        //Act
        //Change Profile User (default) to Manager
        accountAfterUpdate.setProfile(profileContainer, "Manager");

        //Assert
        assertNotEquals(accountBeforeUpdate, accountAfterUpdate);
    }

    /**
     * Test if an account status is set to Active. Should return true, which is equivalent of an inactive profile.
     */

    @Test
    void ensureThatAccountStatusIsSetToActive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null);

        // Act
        accountOne.setStatus(false);
        accountOne.setStatus(true);

        // Assert
        assertEquals(accountOne, accountTwo);
    }

    /**
     * Test if an account status is set to Inactive. Should return false, which is equivalent of an inactive profile.
     */

    @Test
    void ensureThatAccountStatusIsSetToInactive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null);

        // Act
        accountTwo.setStatus(false);

        // Assert
        assertNotEquals(accountOne, accountTwo);
    }


    /**
     * Test if an account exists through a giving email. Should return true.
     */
    @Test
    void ensureThatAnAccountExistsThroughAnEmailAddress() {
        //Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
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
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
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
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
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
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        boolean expected = false;
        //Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);
        //Assert
        assertEquals(expected, result);
    }
}