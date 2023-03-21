package org.switch2022.project.factories;

import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.model.UserStory;

/**
 * Implementation of the FactoryUserStory interface that creates instances of the
 * User Story class.
 */
public class FactoryUserStory implements IFactoryUserStory {
    public UserStory createUserStory(UserStoryCreationDto userStoryCreationDto) {
        if (userStoryCreationDto.userStoryNumber == null || userStoryCreationDto.userStoryText == null ||
                userStoryCreationDto.actor == null) {

            throw new IllegalArgumentException("This value can't be null.");
        } else {
            return new UserStory.UserStoryBuilder(userStoryCreationDto.userStoryNumber)
                    .setUserStoryText(userStoryCreationDto.userStoryText)
                    .setActor(userStoryCreationDto.actor)
                    .build();
        }
    }

    /**
     * This method creates a new User Story object.
     *
     * @param userStoryNumber the number of User Story
     * @param userStoryText   the text of User Story
     * @return a new User Story object with the specified attributes.
     */

    public UserStory createUserStory(String userStoryNumber,
                                     String userStoryText) {
        return new UserStory.UserStoryBuilder(userStoryNumber)
                .setUserStoryText(userStoryText)
                .build();
    }
}
