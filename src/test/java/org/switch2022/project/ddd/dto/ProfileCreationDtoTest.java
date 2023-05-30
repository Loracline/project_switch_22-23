package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfileCreationDtoTest {
    /**
     * Tests the constructor of ProfileCreationDto to ensure that attributes are properly set.
     */
    @Test
    public void constructor_ShouldSetProfileName() {
        // Arrange
        String expectedProfileName = "Administrator";

        // Act
        ProfileCreationDto profile = new ProfileCreationDto(expectedProfileName);

        // Assert
        Assertions.assertEquals(expectedProfileName, profile.profileName);
    }
}
