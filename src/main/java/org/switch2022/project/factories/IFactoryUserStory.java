package org.switch2022.project.factories;

import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.model.UserStory;

/**
 * Interface for an UserStory factory.
 */
public interface IFactoryUserStory {
    /**
     * This method creates a new UserStory object with no return.
     *
     * @param userStoryCreationDto
     */
    UserStory createUserStory(UserStoryCreationDto userStoryCreationDto);

    /**
     * This method creates a new UserStory object with the specified attributes with no return.
     *
     * @param userStoryNumber the number of the User Story
     * @param userStoryText   the text of the User Story
     */
    UserStory createUserStory(String userStoryNumber, String userStoryText);
}
