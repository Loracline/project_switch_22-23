package org.switch2022.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    /**
     * Testing the Constructor
     */
    @Test
    void newAccount_HappyPath() {
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678);
    }

    /**
     * Testing the Setter Method for the attribute ACCOUNT STATUS
     */
    @Test
    void setAccountStatus_Active() {
        // Arrange
        Profile accountProfile = new Profile("User");
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678);

        // Act
        accountOne.updateAccountStatus(true);

        // Assert
        assertTrue(accountOne.isAccountStatusActive());
    }

    @Test
    void setAccountStatus_Inactive() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678);

        // Act
        accountOne.updateAccountStatus(false);

        // Assert
        assertFalse(accountOne.isAccountStatusActive());
    }

    @Test
    void updateAccountProfile_HappyPath() {
        // Arrange
        Account accountOne = new Account("John", "john@isep.ipp.pt", 912345678);
        Account accountTwo = new Account("John", "john@isep.ipp.pt", 912345678);
        Profile profileManagerOne = new Profile("Manager");
        Profile profileManagerTwo = new Profile("Manager");

        //Act
        accountOne.updateAccountProfile(profileManagerOne);
        accountTwo.updateAccountProfile(profileManagerTwo);

        //Assert
        assertEquals(accountOne, accountTwo);
    }
}