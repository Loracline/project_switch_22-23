package org.switch2022.project.ddd.datamodel.JPA.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;

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
        String[] usNumberPart = usNumber.split("_");
        String sprintNumber = usNumberPart[1];
        UsNumber userStoryNumber = new UsNumber(sprintNumber);

        UsText userStoryText = new UsText(userStoryJpa.getUsText());
        Actor actor = new Actor(userStoryJpa.getActor());
        List<AcceptanceCriteria> acceptanceCriteria = new ArrayList<>();
        for(String ac : userStoryJpa.getAcceptanceCriteria()){
            acceptanceCriteria.add(new AcceptanceCriteria(ac));
        }

        String projectNumber = userStoryJpa.getProjectCode();
        String[] projectPart = projectNumber.split("_");
        int project = Integer.parseInt(projectPart[1]);
        Code projectCode = new Code(project);

        UserStory userStory = factory.createUserStory(userStoryNumber,
                userStoryText, actor, acceptanceCriteria, projectCode);

        return userStory;
    }
}
