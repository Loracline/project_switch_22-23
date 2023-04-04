package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.dto.UserStoryCreationDto;

public interface IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param userStoryCreationDto the DTO object containing the necessary information to create a User Story.
     * @param projectCode          the code of the Project where the User Story belongs to.
     * @return the newly created User Story object.
     */
    UserStory createUserStory(UserStoryCreationDto userStoryCreationDto, String projectCode);
}