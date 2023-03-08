package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {
    /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameUserStoryEqualsItself() {
        // Arrange
        UserStory reference = new UserStory("US001", "Manager", "I want to create a profile");
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
        UserStory reference = new UserStory("US001", "Manager", "I want to create a profile");
        UserStory other =  new UserStory("US002", "Manager", "I want to create a profile");
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
    void ensureProfileDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UserStory reference = new UserStory("US001", "Manager", "I want to create a profile");
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
    void ensureProfileDoesNotEqualNull() {
        // Arrange
        UserStory reference = new UserStory("US001", "Manager", "I want to create a profile");
        UserStory other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }


    /*
      METHOD hashCode()
     */

    /**
     * Scenario 1: Two UserStory objects are the same.
     */
    @Test
    public void ensureTwoProfilesHashcodeAreTheSame() {
        // Arrange
        UserStory userStoryOne = new UserStory("US001", "Manager", "I want to create a profile");
        UserStory userStoryTwo = new UserStory("US001", "Manager", "I want to create a profile");

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
    public void ensureTwoProfilesHashcodeAreNotTheSame() {
        // Arrange
        UserStory userStoryOne = new UserStory("US001", "Manager", "I want to create a profile");
        UserStory userStoryThree = new UserStory("US002", "Manager", "I want to create a profile");

        // Act
        int userStoryOneHashCode = userStoryOne.hashCode();
        int userStoryThreeHashCode = userStoryThree.hashCode();

        // Assert
        assertNotEquals(userStoryOneHashCode, userStoryThreeHashCode);
    }
/*
      METHOD hasUserStoryNumber()
     */
    /**
     * Scenario 1: Verify if userStory is the same by checking its userStoryNumber.
     * Expected result:True
     */

    @Test
    public void ensureUserStoryNumberIsTheSame(){
        // Arrange
        UserStory userStoryOne = new UserStory("US001", "Manager", "I want to create a profile");
        boolean expected = true;
        //Act
        boolean result=userStoryOne.hasUserStoryNumber("US001");
        //Assert
        assertEquals(expected,result);
    }
    /**
     * Scenario 1: Verify if userStory is not the same by checking its userStoryNumber.
     * Expected result:false
     */
    @Test
    public void ensureUserStoryNumberIsNotTheSame(){
        // Arrange
        UserStory userStoryOne = new UserStory("US001", "Manager", "I want to create a profile");
        boolean expected = false;
        //Act
        boolean result=userStoryOne.hasUserStoryNumber("US002");
        //Assert
        assertEquals(expected,result);
    }
}