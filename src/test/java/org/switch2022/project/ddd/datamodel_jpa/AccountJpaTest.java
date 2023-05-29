package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.utils.Utils;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountJpaTest {

    @DisplayName("AccountJpa is created successfully")
    @Test
    void ensureAccountJpaIsCreatedSuccessfully() {
        // Arrange
        String email = "josh@email.pt";
        String name = "josh";
        int phoneNumber = 921036438;
        String accountStatus = "active";
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        String profileId = "1";

        // Act
        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, accountStatus, profileId,
                defaultImage);

        // Assert
        BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(accountJpa.getPhoto());

        assertEquals(name, accountJpa.getName());
        assertEquals(email, accountJpa.getEmail());
        assertEquals("921036438", accountJpa.getPhoneNumber());
        assertEquals(accountStatus, accountJpa.getAccountStatus());
        assertImagesAreEqual(defaultImage, bufferedImage);
        assertEquals(profileId, accountJpa.getProfileId());
    }

    /**
     * Asserts that two BufferedImages are equal, pixel by pixel.
     *
     * @param expected the expected BufferedImage
     * @param actual   the actual BufferedImage
     * @throws AssertionError if the BufferedImages are not equal
     */
    private void assertImagesAreEqual(BufferedImage expected, BufferedImage actual) {
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());

        for (int x = 0; x < actual.getWidth(); x++) {
            for (int y = 0; y < actual.getHeight(); y++) {
                assertEquals(expected.getRGB(x, y), actual.getRGB(x, y));
            }
        }
    }
}