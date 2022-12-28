package org.switch2022.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {
    /**
     * Testing the Constructor
     */
    @Test
    void newAccount_HappyPath() {
        Account account_one = new Account("John", "john@isep.ipp.pt", 912345678);
    }

    /**
     * Testing the Setter Method for the attribute ACCOUNT STATUS
     */
    @Test
    void setAccountStatus_Active() {
        // Arrange
        Account account_one = new Account("John", "john@isep.ipp.pt", 912345678);

        // Act
        account_one.updateAccountStatus(true);

        // Assert
        assertTrue(account_one.isAccountStatusActive());
    }

    @Test
    void setAccountStatus_Inactive() {
        // Arrange
        Account account_one = new Account("John", "john@isep.ipp.pt", 912345678);

        // Act
        account_one.updateAccountStatus(false);

        // Assert
        assertFalse(account_one.isAccountStatusActive());
    }
}