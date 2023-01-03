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
     * Testing the Setter Method for the attribute ACCOUNT STATUS
     */
    @Test
    void setAccountStatus_Active() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null, false);

        // Act
        accountOne.updateAccountStatus(true);

        // Assert
        assertTrue(accountOne.accountStatus());
    }

    @Test
    void setAccountStatus_Inactive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678, null, true);

        // Act
        accountOne.updateAccountStatus(false);

        // Assert
        assertFalse(accountOne.accountStatus());
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