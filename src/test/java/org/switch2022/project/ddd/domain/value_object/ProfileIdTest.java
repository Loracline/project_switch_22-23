package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileIdTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of ProfileId is not created because the number passed as argument is
     * null.
     * Should throw an IllegalArgumentException.
     */

    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The profile number must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new ProfileId(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of ProfileId is not created because the number passed as argument is
     * negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNegative() {
        //Arrange
        String expected = "The profile number must not be negative";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new ProfileId(-1));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getProfileId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the profile id attribute of the
     * ProfileId value object.
     */
    @Test
    void ensureProfileIdIsRetrievedSuccessfully() {
        // Arrange
        ProfileId testProfile = new ProfileId( 1);
        String expected = "prf001";

        // Act
        String result = testProfile.getProfileId();

        // Assert
        assertEquals(expected, result);

    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of ProfileId are equal if the value of their attribute business
     * sector id is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoProfileIdInstancesHaveTheSameNumberValue() {
        //Arrange
        ProfileId profileId = new ProfileId(1);
        ProfileId otherProfileId = new ProfileId(1);
        //Act
        boolean result = profileId.sameValueAs(otherProfileId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of ProfileId are not equal if the value of their attribute
     * profile id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoProfileIdInstancesHaveDifferentNumberValues() {
        //Arrange
        ProfileId profileId = new ProfileId(1);
        ProfileId otherProfileId = new ProfileId(2);
        //Act
        boolean result = profileId.sameValueAs(otherProfileId);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProfileIdEqualsItself() {
        // Arrange
        ProfileId reference = new ProfileId(1);
        ProfileId other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same id are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        ProfileId reference = new ProfileId(1);
        ProfileId other = new ProfileId(1);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify if two different objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoDifferentProfileIdInstancesAreNotTheSame() {
        // Arrange
        ProfileId reference = new ProfileId(1);
        ProfileId other = new ProfileId(2);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a ProfileId instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureProfileIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        ProfileId reference = new ProfileId(1);
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a ProfileId and a null object are not the same.
     */
    @Test
    void ensureProfileIdInstanceDoesNotEqualNull() {
        // Arrange
        ProfileId reference = new ProfileId(1);
        ProfileId other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal ProfileId objects are the same.
     */
    @Test
    public void ensureTwoProfileIdInstancesHashcodeAreTheSame() {
        // Arrange
        ProfileId profileIdOne = new ProfileId(1);
        ProfileId profileIdTwo = new ProfileId(1);

        // Act
        int profileIdOneHashCode = profileIdOne.hashCode();
        int profileIdTwoHashCode = profileIdTwo.hashCode();

        // Assert
        assertEquals(profileIdOneHashCode, profileIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different ProfileId objects are not the same.
     */
    @Test
    public void ensureProfileIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        ProfileId profileIdOne = new ProfileId(1);
        ProfileId profileIdThree = new ProfileId(3);

        // Act
        int profileIdOneHashCode = profileIdOne.hashCode();
        int profileIdThreeHashCode = profileIdThree.hashCode();

        // Assert
        assertNotEquals(profileIdOneHashCode, profileIdThreeHashCode);
    }

}

