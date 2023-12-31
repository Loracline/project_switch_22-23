package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;

import java.util.List;

public interface IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param userStoryNumber the number of the userStory.
     * @param userStoryText the description of the userStory.
     * @param actor the actor of the userStory.
     * @param acceptanceCriteria the list of acceptance criteria.
     * @param projectCode          the code of the Project where the User Story belongs to.
     * @return the newly created User Story object.
     */
    UserStory createUserStory(UsNumber userStoryNumber, UsText userStoryText, Actor actor,
                              List<AcceptanceCriteria> acceptanceCriteria,
                              Code projectCode);
}