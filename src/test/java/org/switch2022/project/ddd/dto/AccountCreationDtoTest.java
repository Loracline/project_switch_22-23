package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class AccountCreationDtoTest {

    /**
     * Method : AccountCreationDto().
     * Scenario 1: ensure AccountCreationDto is created.
     */

    @Test
    public void constructor_ShouldSetAttributes() {
        // Arrange
        String expectedName = "John Doe";
        String expectedEmail = "johndoe@example.com";
        int expectedPhoneNumber = 123456789;
        BufferedImage expectedPhoto = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        // Act
        AccountCreationDto account = new AccountCreationDto(expectedName, expectedEmail,
                expectedPhoneNumber, expectedPhoto);

        // Assert
        assertEquals(expectedName, account.name);
        assertEquals(expectedEmail, account.email);
        assertEquals(expectedPhoneNumber, account.phoneNumber);
        assertEquals(expectedPhoto, account.photo);
    }

}