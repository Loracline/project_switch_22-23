package org.switch2022.project.factories;

import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.model.UserStory;

public interface IFactoryUserStory {
    UserStory createUserStory(UserStoryCreationDto userStoryCreationDto);

    UserStory createUserStory(String userStoryNumber, String actor, String userStoryText);
}
