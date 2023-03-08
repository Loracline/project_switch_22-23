package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SprintBacklogTest {

  /**
   * Method equals()
   */

  /**
   * Scenario 1: Verify if the same object equals itself.
   */

  @Test
  void ensureSameSprintBacklogEqualsItself() {
    // Arrange
    SprintBacklog reference = new SprintBacklog();
    SprintBacklog other = reference;
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
  void ensureTwoSprintBacklogsAreNotTheSame() {
    // Arrange
    SprintBacklog reference = new SprintBacklog();
    reference.userStories.add(0, new UserStory("US001", "Manager",
            "I want to create a profile"));
    SprintBacklog other = new SprintBacklog();
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

  @Test
  void ensureSprintBacklogsAreNotEqualOtherTypeOfObject() {
    // Arrange
    SprintBacklog reference = new SprintBacklog();
    reference.userStories.add(0, new UserStory("US001", "Manager",
            "I want to create a profile"));
    Object other = new SprintBacklog();
    boolean expected = false;

    // Act
    boolean result = reference.equals(other);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Scenario 4: Verify if a SprintBacklog and a null object are not the same.
   */

  @Test
  void ensureProfileDoesNotEqualNull() {
    // Arrange
    SprintBacklog reference = new SprintBacklog();
    SprintBacklog other = null;
    boolean expected = false;

    // Act
    boolean result = reference.equals(other);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Method HashCode
   */

  /**
   * Scenario 1: Two SprintBacklog objects are the same.
   */

  @Test
  public void ensureTwoProfilesHashcodeAreTheSame() {
    // Arrange
    SprintBacklog sprintBacklogOne = new SprintBacklog();
    SprintBacklog sprintBacklogTwo = new SprintBacklog();

    // Act
    int sprintBacklogOneHashCode = sprintBacklogOne.hashCode();
    int sprintBacklogTwoHashCode = sprintBacklogTwo.hashCode();

    // Assert
    assertEquals(sprintBacklogOneHashCode, sprintBacklogTwoHashCode);
  }

  /**
   * Scenario 2: Two SprintBacklog objects are not the same.
   */

  @Test
  public void ensureTwoProfilesHashcodeAreNotTheSame() {
    // Arrange
    SprintBacklog sprintBacklogOne = new SprintBacklog();
    SprintBacklog sprintBacklogTwo = new SprintBacklog();
    sprintBacklogTwo.userStories.add(0,new UserStory("US001", "Manager",
            "I want to create a profile"));

    // Act
    int sprintBacklogOneHashCode = sprintBacklogOne.hashCode();
    int sprintBacklogTwoHashCode = sprintBacklogTwo.hashCode();

    // Assert
    assertNotEquals(sprintBacklogOneHashCode, sprintBacklogTwoHashCode);
  }
}