package org.switch2022.project.ddd.domain.model.user_story;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
@Component
public class FactoryUserStory implements IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param userStoryNumber the number of the userStory.
     * @param userStoryText the description of the userStory.
     * @param actor the actor of the userStory.
     * @param priority the priority of the userStory.
     * @param projectCode          the code of the Project where the User Story belongs to.
     * @return the newly created User Story object.
     */
    @Override
    public UserStory createUserStory(UsNumber userStoryNumber, UsText userStoryText, Actor actor, int priority, Code projectCode) {

        UserStory userStory = new UserStory(new UsId(projectCode.getCode(), userStoryNumber.getUserStoryNumber()));

        userStory.setValidUserStory(
                new UsNumber(userStoryNumber.getUserStoryNumber()),
                new UsText(userStoryText.getUserStoryText()),
                new Actor(actor.getActor()));

        return userStory;
    }
}
