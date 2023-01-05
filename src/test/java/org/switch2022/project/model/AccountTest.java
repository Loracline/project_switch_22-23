package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    /**
     * Testing the Constructor
     */
    @Test
    void newAccount_HappyPath() {
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null, true);
    }

    /**
     * Testing the Setter Method for the attribute ACCOUNT STATUS by comparing two accounts contents.
     */
    @Test
    void setAccountStatusActive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null, false);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null, true);

        // Act
        accountOne.setStatus(true);

        // Assert
        assertEquals(accountOne,accountTwo);
    }

    @Test
    void setAccountStatusInactive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null, false);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null, true);

        // Act
        accountTwo.setStatus(false);

        // Assert
        assertEquals(accountOne,accountTwo);
    }

    @Test
    void setAccountProfile_HappyPath() {
        // Arrange
        Account accountBeforeUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null, true); //Default Profile: User
        Account accountAfterUpdate = new Account("John", "john@isep.ipp.pt", 912345678, null, true); //Default Profile: User

        //Create Manager profile
        Profile profileManagerOne = new Profile("Manager");

        //Act
        //Change Profile User (default) to Manager
        accountAfterUpdate.setProfile(profileManagerOne);

        //Assert
        assertNotEquals(accountBeforeUpdate, accountAfterUpdate);
    }

    @Test
    void getEmail_HappyPath() {
        // Arrange
        Account account = new Account("John", "john@isep.ipp.pt", 912345678, null, true);
        String expected = "john@isep.ipp.pt";

        //ACT
        String result = account.getEmail();

        //ASSERT
        assertEquals(expected,result);
    }

    @Test
    void setProfile_HappyPath() {
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null, true); //Default Profile: User
        accountOne.setProfile(new Profile("Manager"));
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678, null, true);
        assertNotEquals(accountOne, accountTwo);
    }
}