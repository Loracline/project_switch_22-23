package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    /**
     * METHOD getPhoto()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the photo attribute of the
     * Photo value object.
     */
    @Test
    void ensurePhotoIsRetrievedSuccessfully() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo photo = new Photo(value);
        BufferedImage expected = value;

        // Act
        BufferedImage result = photo.getPhoto();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Photo are equal if the value of their attribute photo is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoPhotoInstancesHaveTheSamePhotoValue() {
        //Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo photo = new Photo(value);
        Photo otherPhoto = new Photo(value);

        //Act
        boolean result = photo.sameValueAs(otherPhoto);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Photo are not equal if the value of their attribute photo is not the
     * same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPhotoInstancesHaveDifferentPhotoValues() {
        //Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        BufferedImage otherValue = new BufferedImage(10, 10, 2);
        Photo photo = new Photo(value);
        Photo otherPhoto = new Photo(otherValue);
        //Act
        boolean result = photo.sameValueAs(otherPhoto);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSamePhotoEqualsItself() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo reference = new Photo(value);
        Photo other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same photo are equal.
     */
    @Test
    void ensureTwoInstancesWithSamePhotoAreEqual() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo reference = new Photo(value);
        Photo other = new Photo(value);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify that two objects with different photos are not equal.
     */
    @Test
    void ensureTwoDifferentPhotoInstancesAreNotTheSame() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        BufferedImage otherValue = new BufferedImage(10, 10, 2);
        Photo reference = new Photo(value);
        Photo other = new Photo(otherValue);
        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Photo instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensurePhotoDoesNotEqualOtherTypeOfObject() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo reference = new Photo(value);
        BufferedImage other = new BufferedImage(1,1,1);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a Photo and a null object are not the same.
     */
    @Test
    void ensureEmailInstanceDoesNotEqualNull() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo reference = new Photo(value);

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Photo objects are the same.
     */
    @Test
    public void ensureTwoEmailInstancesHashcodeAreTheSame() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        Photo photoOne = new Photo(value);
        Photo photoTwo = new Photo(value);

        // Act
        int emailOneHashCode = photoOne.hashCode();
        int emailTwoHashCode = photoTwo.hashCode();

        // Assert
        assertEquals(emailOneHashCode, emailTwoHashCode);
    }

    /**
     * Scenario 2: Two different Photo objects are not the same.
     */
    @Test
    public void ensureTwoEmailInstancesHashcodeAreNotTheSame() {
        // Arrange
        BufferedImage value = new BufferedImage(10, 10, 1);
        BufferedImage otherValue = new BufferedImage(10, 10, 2);
        Photo photoOne = new Photo(value);
        Photo photoTwo = new Photo(otherValue);

        // Act
        int emailOneHashCode = photoOne.hashCode();
        int emailTwoHashCode = photoTwo.hashCode();

        // Assert
        assertNotEquals(emailOneHashCode, emailTwoHashCode);
    }
}