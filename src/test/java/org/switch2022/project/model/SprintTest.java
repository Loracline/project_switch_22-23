package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.switch2022.project.model.UserStory.createUserStory;


class SprintTest {

  /**
   * METHOD estimateEffortUserStory(userStoryDto, effort)
   * <p>
   * Scenario 1: sets the effort of a UserStory.
   */

  @Test
  void ensureEffortIsIsSetForUserStory() {
    //Arrange
    LocalDate date = LocalDate.of(2021, 9, 13);
    FactoryPeriod factoryPeriod = new IFactoryPeriod();
    Sprint sprint = Sprint.createSprint(date, 2, "S55", factoryPeriod);

    UserStory userStory = createUserStory("US001", "Manager",
            "I want to create a profile");
    sprint.addUserStoryToSprintBacklog(userStory);

    UserStoryDto userStoryDto = new UserStoryDto("US001", "Manager",
            "I want to create a profile");
    Effort effort = Effort.THREE;

    //Act
    boolean result = sprint.estimateEffortUserStory(userStoryDto, effort);

    //Assert
    assertTrue(result);
  }
}