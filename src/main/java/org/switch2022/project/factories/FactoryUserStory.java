package org.switch2022.project.factories;

import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.model.UserStory;

public class FactoryUserStory implements IFactoryUserStory {
    public UserStory createUserStory(UserStoryCreationDto userStoryCreationDto) {
        if (userStoryCreationDto.userStoryNumber == null || userStoryCreationDto.userStoryText == null ||
                userStoryCreationDto.actor == null) {

            throw new IllegalArgumentException("Valor não pode ser null.");
        } else {
            return new UserStory.UserStoryBuilder(userStoryCreationDto.userStoryNumber)
                    .setUserStoryText(userStoryCreationDto.userStoryText)
                    .setActor(userStoryCreationDto.actor)
                    .build();
        }

    }

    public UserStory createUserStory(String userStoryNumber,
                                     String userStoryText) {
        return new UserStory.UserStoryBuilder(userStoryNumber)
                .setUserStoryText(userStoryText)
                .build();
    }
}
