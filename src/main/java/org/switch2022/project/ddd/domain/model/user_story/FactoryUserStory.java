package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.domain.value_object.Actor;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.domain.value_object.UsNumber;
import org.switch2022.project.ddd.domain.value_object.UsText;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.utils.Validate;

public class FactoryUserStory implements IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param userStoryCreationDto the DTO object containing the necessary information to create a User Story.
     * @return the newly created User Story object.
     */
    @Override
    public UserStory createUserStory(UserStoryCreationDto userStoryCreationDto, String projectCode) {
        UserStory userStory;

        Validate.notNullOrEmptyOrBlank(userStoryCreationDto.userStoryNumber, "user story number");
        Validate.notNullOrEmptyOrBlank(userStoryCreationDto.userStoryText, "user story text");
        Validate.notNullOrEmptyOrBlank(userStoryCreationDto.actor, "actor");

        userStory = new UserStory(new UsId(userStoryCreationDto.userStoryNumber, projectCode));
        userStory.setUsNumber(new UsNumber(userStoryCreationDto.userStoryNumber));
        userStory.setUsText(new UsText(userStoryCreationDto.userStoryText));
        userStory.setActor(new Actor(userStoryCreationDto.actor));


        return userStory;
    }
}
