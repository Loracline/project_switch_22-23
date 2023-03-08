package org.switch2022.project.model;


import org.junit.jupiter.api.Test;
import org.switch2022.project.utils.Effort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.switch2022.project.model.UserStory.createUserStory;

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
    UserStory reference = createUserStory("US001", "Manager", "I want to create a profile");
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
    UserStory reference = createUserStory("US001", "Manager", "I want to create a profile");
    UserStory other = createUserStory("US002", "Manager", "I want to create a profile");
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
    UserStory reference = createUserStory("US001", "Manager", "I want to create a profile");
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
    UserStory reference = createUserStory("US001", "Manager", "I want to create a profile");
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
  public void ensureTwoUserStoriesHashcodeAreTheSame() {
    // Arrange
    UserStory userStoryOne = createUserStory("US001", "Manager", "I want to create a profile");
    UserStory userStoryTwo = createUserStory("US001", "Manager", "I want to create a profile");

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
    UserStory userStoryOne = createUserStory("US001", "Manager", "I want to create a profile");
    UserStory userStoryThree = createUserStory("US002", "Manager", "I want to create a profile");

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
  public void ensureUserStoryNumberIsTheSame() {
    // Arrange
    UserStory userStoryOne = createUserStory("US001", "Manager", "I want to create a profile");
    boolean expected = true;
    //Act
    boolean result = userStoryOne.hasUserStoryNumber("US001");
    //Assert
    assertEquals(expected, result);
  }

  /**
   * Scenario 1: Verify if userStory is not the same by checking its userStoryNumber.
   * Expected result:false
   */
  @Test
  public void ensureUserStoryNumberIsNotTheSame() {
    // Arrange
    UserStory userStoryOne = createUserStory("US001", "Manager", "I want to create a profile");
    boolean expected = false;
    //Act
    boolean result = userStoryOne.hasUserStoryNumber("US002");
    //Assert
    assertEquals(expected, result);
  }

  /**
   * Method setEffort and getEffort.
   */

  /**
   * Scenario 1: Verify if the effort of userStoryOne is set.
   * Expected result: effort of userStoryOne is equal to effort value.
   */

  @Test
  void ensureEffortIsSet() {
    // Arrange
    UserStory userStoryOne = createUserStory("US001", "Manager",
            "I want to create a profile");
    // Act
    userStoryOne.setEffort(Effort.TWO);
    Effort effortOfUserStory = userStoryOne.getEffort();
    // Assert
    assertEquals(effortOfUserStory, Effort.TWO);
  }

  /**
   * Scenario 2: Verify if the effort of a userStory is the same as other userStory by setting the efforts respectively.
   * Expected result: effort of userStoryOne is equal to effort of userStoryTwo.
   */

  @Test
  void ensureEffortIsNotTheSameForTwoDifferentUserStories() {
    // Arrange
    UserStory userStoryOne = createUserStory("US001", "Manager",
            "I want to create a profile");
    UserStory userStoryTwo = createUserStory("US002", "Manager",
            "I want to create a project");
    // Act
    Effort effortOfUserStoryOne = userStoryOne.getEffort();
    userStoryOne.setEffort(Effort.TWO);
    Effort effortOfUserStoryTwo = userStoryTwo.getEffort();
    userStoryTwo.setEffort(Effort.THIRTEEN);
    // Assert
    assertEquals(effortOfUserStoryOne, effortOfUserStoryTwo);
  }
}