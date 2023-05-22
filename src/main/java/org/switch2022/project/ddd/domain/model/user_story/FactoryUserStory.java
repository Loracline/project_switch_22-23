package org.switch2022.project.ddd.domain.model.user_story;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.*;

import java.util.List;

@Component
public class FactoryUserStory implements IFactoryUserStory {
    /**
     * This method creates a new User Story based on the provided UserStoryCreationDto.
     *
     * @param usNumber the number of the userStory.
     * @param usText the description of the userStory.
     * @param actor the actor of the userStory.
     * @param projectCode the code of the Project where the User Story belongs to.
     * @param acceptanceCriteria the list of acceptance criteria.
     * @return the newly created User Story object.
     */
    @Override
    public UserStory createUserStory(UsNumber usNumber, UsText usText, Actor actor,
                                     List<AcceptanceCriteria> acceptanceCriteria, Code projectCode) {

        return new UserStory(projectCode, usNumber, actor, usText, acceptanceCriteria);
    }
}
