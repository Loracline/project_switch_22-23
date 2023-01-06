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
     * Testing the Setter Method for the attribute ACCOUNT STATUS by comparing two accounts contents.
     */
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

    @Test
    void ensureAccountProfileIsSetSuccessfully() {
        // Arrange
        Account accountBeforeUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null); //Default Profile: User
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
    void ensureProfileIsSetSuccessfully() throws IOException {
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/domainModel_v1_Jan05_2023.png"));
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, photo); //Default Profile: User
        accountOne.setProfile(new Profile("Manager"));
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, photo);
        assertNotEquals(accountOne, accountTwo);
    }

    @Test
    void ensureThatPhotoIsSet() throws IOException {
        //ARRANGE
        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/domainModel_v1_Jan05_2023.png"));
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Account accountWithPhoto = new Account("John", "john@isep.ipp.pt", 912345678,
                photo);
        //ACT
        accountOne.setPhoto(photo);
        //ASSERT
        assertEquals(accountWithPhoto, accountOne);
    }

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
        assertEquals(expected,result);
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
        assertEquals(expected,result);
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
        assertEquals(expected,result);
    }

    @Test
    void ensureAccountDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Account reference = new Account("John", "john@isep.ipp.pt", 912345678, null);
        Profile other = new Profile("John");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected,result);
    }
}