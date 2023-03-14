package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.Effort;
import org.switch2022.project.utils.Status;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class UserStoryTest {
    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameUserStoryEqualsItself() {
        // Arrange
        UserStory reference =
                (new UserStory.UserStoryBuilder("US001").setUserStoryText("Hello").
                        setActor("Manager").build());
        UserStory other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoUserStoriesAreNotTheSame() {
        // Arrange
        UserStory reference = (new UserStory.UserStoryBuilder("US001").build());
        UserStory other = (new UserStory.UserStoryBuilder("US002").build());
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify if a UserStory and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureUserStoryDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UserStory reference = (new UserStory.UserStoryBuilder("US001").build());
        String other = "User";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a UserStory and a null object are not the same.
     */
    @Test
    void ensureUserStoryDoesNotEqualNull() {
        // Arrange
        UserStory reference = (new UserStory.UserStoryBuilder("US001").build());
        UserStory other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two UserStory objects are the same.
     */
    @Test
    public void ensureTwoUserStoriesHashcodeAreTheSame() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());
        UserStory userStoryTwo = (new UserStory.UserStoryBuilder("US001").build());

        // Act
        int userStoryOneHashCode = userStoryOne.hashCode();
        int userStoryTwoHashCode = userStoryTwo.hashCode();

        // Assert
        assertEquals(userStoryOneHashCode, userStoryTwoHashCode);
    }

    /**
     * Scenario 2: Two UserStory objects are not the same.
     */
    @Test
    public void ensureTwoUserStoriesHashcodeAreNotTheSame() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());
        UserStory userStoryThree = (new UserStory.UserStoryBuilder("US002").build());

        // Act
        int userStoryOneHashCode = userStoryOne.hashCode();
        int userStoryThreeHashCode = userStoryThree.hashCode();

        // Assert
        assertNotEquals(userStoryOneHashCode, userStoryThreeHashCode);
    }

    /**
     * METHOD hasUserStoryNumber()
     * <br>
     * Scenario 1: Verify if userStory is the same by checking its userStoryNumber.
     * Expected result:True
     */
    @Test
    public void ensureUserStoryNumberIsTheSame() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());
        boolean expected = true;

        // Act
        boolean result = userStoryOne.hasUserStoryNumber("US001");

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2l: Verify if userStory is not the same by checking its userStoryNumber.
     * Expected result:false
     */
    @Test
    public void ensureUserStoryNumberIsNotTheSame() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());
        boolean expected = false;

        // Act
        boolean result = userStoryOne.hasUserStoryNumber("US002");

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method setEffort and getEffort.
     * <br>
     * Scenario 1: Verify if the effort of userStoryOne is set.
     * Expected result: effort of userStoryOne is equal to effort value.
     */
    @Test
    void ensureEffortIsSet() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());

        // Act
        userStoryOne.setEffort(Effort.TWO);

        // Assert
        assertEquals(Effort.TWO, userStoryOne.getEffort());
    }

    /**
     * Scenario 2: Verify if the effort of a userStoryOne is the same as other userStoryTwo
     * by setting the efforts respectively.
     * Expected result: effort of userStoryOne is equal to the effort of userStoryTwo.
     */
    @Test
    void ensureEffortIsTheSameForTwoDifferentUserStories() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());
        UserStory userStoryTwo = (new UserStory.UserStoryBuilder("US002").build());

        // Act
        userStoryOne.setEffort(Effort.TWO);
        userStoryTwo.setEffort(Effort.TWO);

        // Assert
        assertEquals(userStoryOne.getEffort(), userStoryTwo.getEffort());
    }

    /**
     * Scenario 3: Verify if the effort of a userStoryOne is different of userStoryTwo
     * by setting the efforts respectively.
     * Expected result: effort of userStoryOne is not equal to the effort of userStoryTwo.
     */
    @Test
    void ensureEffortIsNotTheSameForTwoDifferentUserStories() {
        // Arrange
        UserStory userStoryOne = (new UserStory.UserStoryBuilder("US001").build());
        UserStory userStoryTwo = (new UserStory.UserStoryBuilder("US002").build());

        // Act
        userStoryOne.setEffort(Effort.TWO);
        userStoryTwo.setEffort(Effort.THIRTEEN);
        boolean isEquals = (userStoryOne.getEffort().equals(userStoryTwo.getEffort()));

        // Assert
        assertFalse(isEquals);
    }

    /**
     * Scenario 4: Verify if the effort of the userStory is not set.
     * Expected result: effort of userStory is not set.
     */
    @Test
    void ensureEffortIsNotSet() {
        // Arrange
        UserStory userStoryDouble = mock(UserStory.class);

        // Act
        doNothing().when(userStoryDouble).setEffort(Effort.FIVE);

        // Assert
        assertFalse(false);
    }


    /**
     * METHOD getUserStoryNumber()
     * <br>
     * Scenario 1: Verify that the user story number returned is the same of the User
     * Story.
     */
    @Test
    void ensureUserStoryNumberIsRetrievedSuccessfully() {
        // Arrange
        UserStory userStoryOne = new UserStory.UserStoryBuilder("US001").build();
        String expected = "US001".toLowerCase();

        // Act
        String result = userStoryOne.getUserStoryNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getActor()
     * <br>
     * Scenario 1: Verify that the actor returned is the same of the User Story.
     */
    @Test
    void ensureActorIsRetrievedSuccessfully() {
        // Arrange
        UserStory userStoryOne = new UserStory.UserStoryBuilder("US001")
                .setActor("Manager").build();
        String expected = "Manager".toLowerCase();

        // Act
        String result = userStoryOne.getActor();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getUserStoryText()
     * <br>
     * Scenario 1: Verify that the user story text returned is the same of the User Story.
     */
    @Test
    void ensureUserStoryTextIsRetrievedSuccessfully() {
        // Arrange
        UserStory userStoryOne =
                new UserStory.UserStoryBuilder("US001")
                        .setUserStoryText("I want to create a profile").build();
        String expected = "I want to create a profile".toLowerCase().trim();

        // Act
        String result = userStoryOne.getUserStoryText();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD setStatus() and getStatus()
     * <br>
     * Scenario 1: Verify that the status returned is the same of the User Story.
     */
    @Test
    void ensureStatusIsSetAndRetrievedSuccessfully() {
        // Arrange
        Status status = Status.PLANNED;
        UserStory userStoryOne = new
                UserStory.UserStoryBuilder("US001").build();

        // Act
        userStoryOne.setStatus(status.toString());
        String expected = userStoryOne.getStatus();

        // Assert
        assertEquals(expected, userStoryOne.getStatus());
    }
}