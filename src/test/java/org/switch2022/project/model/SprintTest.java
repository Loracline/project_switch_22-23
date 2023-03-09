package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.utils.Effort;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SprintTest {

  @Test
  void ensureEffortIsIsSetForUserStory() {
    //Arrange
    LocalDate date = LocalDate.of(2021, 9, 13);
    FactoryPeriod factoryPeriod = new IFactoryPeriod();

    Sprint sprint = Sprint.createSprint(date,2,"S55",factoryPeriod);

    UserStoryDto userStoryDto = new UserStoryDto("US001", "Manager",
            "I want to create a profile");
    Effort effort = Effort.THREE;
    UserStory userStory = UserStory.createUserStory("US001", "Manager",
            "I want to create a profile");
    sprint.addUserStoryToSprintBacklog(userStory);

    //Act
    boolean result = sprint.estimateEffortUserStory(userStoryDto,effort);

    //Assert
    assertTrue(result);
  }
}