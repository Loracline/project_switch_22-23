package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.utils.Utils;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountJpaTest {

    /**
     * Tests for the equals() method.
     *
     * Scenario 1: object equals itself, should return true.
     */
    @DisplayName("Equals test for AccountJpa: account equals itself")
    @Test
    void ensureThatSameObjectEqualsItself() {
        //Arrange
        String email = "josh@email.pt";
        String name = "josh";
        int phoneNumber = 921036438;
        String accountStatus = "active";
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        String profileId = "PRF001";
        AccountJpa reference = new AccountJpa(email, name, phoneNumber, accountStatus,
                profileId,
                defaultImage);
        boolean expected = true;

        //Act
        boolean result = reference.equals(reference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: two AccountJpa objects are not the same. Should return false.
     */
    @DisplayName("Equals test for AccountJpa: two profiles are the same")
    @Test
    void ensureThatProfileJpaAreNotTheSame() {
        //Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa reference = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        AccountJpa other = new AccountJpa("joshSilva@email.pt", "Josh Silva", 921036432,
                "active", "PRF001",
                defaultImage);
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: AccountJpa object does not equal another type of object. Should return
     * false.
     */
    @DisplayName("Equals test for AccountJpa: not equals another object")
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatProfileJpaDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa reference = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        String other = "Head of Software Development";

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: object does not equal null. Should return false
     */
    @DisplayName("Equals test for AccountJpa: not null")
    @Test
    void ensureThatObjectDoesNotEqualNull() {
        //Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa reference = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        Typology other = null;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: object equals another with same id.
     */
    @DisplayName("Equals test for AccountJpa: same id")
    @Test
    void ensureThatObjectEqualsAnotherWithSameId() {
        //Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa reference = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        AccountJpa other = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests for the hashCode() method.
     */
    @DisplayName("Hashcode test for AccountJpa")
    @Test
    public void testHashCodeProjectTypology() {
        //Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa obj1 = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        AccountJpa obj2 = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        AccountJpa obj3 = new AccountJpa("joshSilva@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);

        //Act and Assert
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

    /**
     * Test for method getEmail().
     */
    @DisplayName("Get AccountJpa email")
    @Test
    void ensureAccountJpaEmailIsRetrieved() {
        // Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa account = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        String expected = "josh@email.pt";

        // Act
        String result = account.getEmail();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for method getName().
     */
    @DisplayName("Get AccountJpa name")
    @Test
    void ensureAccountJpaNameIsRetrieved() {
        // Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa account = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        String expected = "josh";

        // Act
        String result = account.getName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for method getPhoneNumber().
     */
    @DisplayName("Get AccountJpa phone number")
    @Test
    void ensureAccountJpaPhoneNumberIsRetrieved() {
        // Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa account = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        String expected = "921036438";

        // Act
        String result = account.getPhoneNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for method getAccountStatus().
     */
    @DisplayName("Get AccountJpa status")
    @Test
    void ensureAccountJpaStatusIsRetrieved() {
        // Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa account = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        String expected = "active";

        // Act
        String result = account.getAccountStatus();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Get AccountJpa profileId")
    @Test
    void ensureAccountJpaProfileIdIsRetrieved() {
        // Arrange
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        AccountJpa account = new AccountJpa("josh@email.pt", "josh", 921036438,
                "active", "PRF001", defaultImage);
        String expected = "PRF001";

        // Act
        String result = account.getProfileId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests for the constructor and getPhoto()
     */
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

    /**
     * Test for no args constructor.
     */
    @DisplayName("Empty Constructor Testing")
    @Test
    void testEmptyConstructor() {
        // Create an instance using the empty constructor
        AccountJpa account = new AccountJpa();

        // Verify that the attributes are initialized with default values
        assertNull(account.getEmail());
        assertNull(account.getName());
        assertNull(account.getPhoneNumber());
        assertNull(account.getAccountStatus());
        assertNull(account.getPhoto());
        assertNull(account.getProfileId());
    }
}