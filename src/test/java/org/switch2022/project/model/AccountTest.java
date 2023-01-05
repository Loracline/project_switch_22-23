package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Profile;

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
        accountAfterUpdate.updateAccountProfile(profileManagerOne);

        //Assert
        assertNotEquals(accountBeforeUpdate, accountAfterUpdate);
    }
}