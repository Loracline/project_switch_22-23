package org.switch2022.project.ddd.domain.model.user_story;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.Actor;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.domain.value_object.UsNumber;
import org.switch2022.project.ddd.domain.value_object.UsText;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
@Component
public class FactoryUserStory implements IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param userStoryCreationDto the DTO object containing the necessary information to create a User Story.
     * @param projectCode          the code of the Project where the User Story belongs to.
     * @return the newly created User Story object.
     */
    @Override
    public UserStory createUserStory(UserStoryCreationDto userStoryCreationDto, String projectCode) {

        UserStory userStory = new UserStory(new UsId(projectCode, userStoryCreationDto.userStoryNumber));

        userStory.setValidUserStory(
                new UsNumber(userStoryCreationDto.userStoryNumber),
                new UsText(userStoryCreationDto.userStoryText),
                new Actor(userStoryCreationDto.actor));

        return userStory;
    }
}
