package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryJpa;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Assembler class responsible for converting UserStory instances to UserStoryJpa instances and vice versa.
 */

@Service
public class UserStoryDomainDataAssembler {

    @Autowired
    IFactoryUserStory factory;

    /**
     * Converts a UserStory instance to a UserStoryJpa instance.
     *
     * @param userStory The UserStory instance to be converted.
     * @return The converted UserStoryJpa instance.
     */

    public UserStoryJpa toData(UserStory userStory) {
        UserStoryJpa userStoryJpa = new UserStoryJpa(userStory.getUsId(), userStory.getAcceptanceCriteria(),
                userStory.getUsNumber(), userStory.getActor(), userStory.getUsText(), userStory.getStatus(),
                userStory.getProjectCode());
        return userStoryJpa;
    }

    /**
     * Converts a UserStoryJpa instance to a UserStory instance.
     *
     * @param userStoryJpa The UserStoryJpa instance to be converted.
     * @return The converted UserStory instance.
     */

    public UserStory toDomain (UserStoryJpa userStoryJpa){

        String usNumber = userStoryJpa.getUsNumber();

        UsNumber userStoryNumber = new UsNumber(Integer.toString(Utils.getIntFromAlphanumericString (usNumber,"us")));

        UsText userStoryText = new UsText(userStoryJpa.getUsText());
        Actor actor = new Actor(userStoryJpa.getActor());
        List<AcceptanceCriteria> acceptanceCriteria = new ArrayList<>();
        for(String ac : userStoryJpa.getAcceptanceCriteria()){
            acceptanceCriteria.add(new AcceptanceCriteria(ac));
        }

        String projectNumber = userStoryJpa.getProjectCode();

        Code projectCode = new Code(Utils.getIntFromAlphanumericString(projectNumber, "p"));

        UserStory userStory = factory.createUserStory(userStoryNumber,
                userStoryText, actor, acceptanceCriteria, projectCode);

        return userStory;
    }
}
