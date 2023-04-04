package org.switch2022.project.ddd.domain.model.user_story;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Effort;
import org.switch2022.project.ddd.domain.value_object.UsId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {
    /**
     * Constructor
     * <br>
     * Scenario 1: An User Story is not created because the ID is null.
     */

    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheIdIsNull() {
        // Arrange
        UsId usId = null;
        String expected = "User Story's ID can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(usId));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameUserStoryEqualsItself() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same id are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(usId);
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object User Story does not equal null.
     */
    @Test
    void ensureUserStoryDoesNotEqualNull() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        boolean expected = false;

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object UserStory does not equal other type of object.
     */
    @Test
    void ensureUserStoryDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UsId other = mock(UsId.class);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal UserStory objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualUserStoryInstancesHaveTheSameHashcode() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId otherUsId = usId;
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different UserStory objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentUserStoryInstancesHaveDifferentHashcode() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Verify that two instances of UserStory are equal if the value of their
     * attribute user story id is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoUserStoryInstancesHaveTheSameIdValue() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        UserStory other = new UserStory(usId);
        boolean expected = true;

        // Act
        boolean result = userStory.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two instances of UserStory are not equal if the value of
     * their attribute user story id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoUserStoryInstancesDoNotHaveTheSameIdValue() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
        boolean expected = false;

        // Act
        boolean result = userStory.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getUsId()
     * <br>
     * Scenario 1: Verify that the returned user story id is the same of the User
     * Story.
     */

    @Test
    void ensureUserStoryIdIsRetrievedSuccessfully() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        String expected = usId.toString();

        // Act
        String result = userStory.getUsId().toString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD toString()
     * <br>
     * Scenario 1: Verify that String has all the user story information.
     */
    @Test
    void ensureThatUserStoryUSInformationIsRetrievedSuccessfullyAsAString() {
        // Arrange
        UsId usId = mock(UsId.class);
        when(usId.toString()).thenReturn("P1US001");

        UserStory userStory = new UserStory(usId);

        String expected = "UserStory{usId=P1US001, usNumber=null, actor=null, " +
                "usText=null, status=null, acceptanceCriteria=null}";

        // Act
        String result = userStory.toString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD setEffort()
     * <br>
     * Scenario 1: Verify that the effort is set successfully.
     */
    @Test
    void ensureEffortIsSet() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);

        // Act
        userStory.setEffort(2);

        // Assert
        assertEquals(Effort.TWO, userStory.getEffort());
    }

    /**
     * Scenario 2: Verify that the effort is not set successfully.
     */
    @Test
    void ensureEffortIsNotSet() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userStory.setEffort(41);
        });

        // Assert
        assertEquals("Effort estimate was not successful", exception.getMessage());
    }
}
