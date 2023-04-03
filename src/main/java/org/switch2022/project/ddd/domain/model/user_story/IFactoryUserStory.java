package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.dto.UserStoryCreationDto;

public interface IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param userStoryCreationDto the DTO object containing the necessary information to create a User Story.
     * @return the newly created User Story object.
     */
    UserStory createUserStory(UserStoryCreationDto userStoryCreationDto, String projectCode);
}
