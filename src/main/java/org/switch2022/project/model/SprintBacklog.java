package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class SprintBacklog, holds a set of userStories.
 */

public class SprintBacklog {

  /**
   * Attributes of the class SprintBacklog, according to the Class Diagram.
   */

  List<UserStory> userStories = new ArrayList<>();


  /**
   * Constructor of the class SprintBacklog.
   */
  public SprintBacklog() {
  }

  /**
   * This method checks if two instances of SprintBacklog are equal by comparing
   * the userStories.
   *
   * @param o SprintBacklog instance to compare with.
   * @return TRUE if the two have the same attributes, and FALSE otherwise.
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SprintBacklog)) return false;
    SprintBacklog that = (SprintBacklog) o;
    return Objects.equals(userStories, that.userStories);
  }

  /**
   * The hashCode() method is used to generate a unique hash code for an
   * object, based on the object's state.
   *
   * @return a unique value that represents the object.
   */

  @Override
  public int hashCode() {
    return Objects.hash(userStories);
  }
}
