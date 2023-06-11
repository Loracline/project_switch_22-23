package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryInSprintJpa;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.sprint.ISprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Assembler class responsible for converting Sprint instances to SprintJpa instances
 * and vice versa.
 */
@Service
public class SprintDomainDataAssembler {
    @Autowired
    ISprintFactory factory;
    /**
     * Converts a Sprint instance to a SprintJpa instance.
     *
     * @param sprint The Sprint instance to be converted.
     * @return The converted SprintJpa instance.
     */
    public SprintJpa toData(Sprint sprint) {
        SprintJpa sprintJpa = new SprintJpa(sprint.getSprintId(),
                sprint.getFullSprintNumber(), sprint.getProjectCode(),
                sprint.getStartDate(), sprint.getEndDate(), sprint.getStatus());

        List<UserStoryInSprint> userStoriesInSprint =
                sprint.getUserStoriesInSprint();
        List<UserStoryInSprintJpa> userStoriesInSprintJpa =
                sprintJpa.getUserStoriesInSprint();
        for (UserStoryInSprint userStory: userStoriesInSprint) {
            UserStoryInSprintJpa userStoryInSprintJpa =
                    new UserStoryInSprintJpa(userStory.getUsId().toString(),
                    userStory.getEffort());
            userStoriesInSprintJpa.add(userStoryInSprintJpa);
        }
        return sprintJpa;
    }
    /**
     * Converts a SprintJpa instance to a Sprint instance.
     *
     * @param sprintJpa The SprintJpa instance to be converted.
     * @return The converted Sprint instance.
     */
    public Sprint toDomain (SprintJpa sprintJpa) {
               int projectNumber = Utils.getIntFromAlphanumericString
                (sprintJpa.getProjectCode(), "p");
        Code projectCode = new Code(projectNumber);

        int sprintNumberInt = Utils.getIntFromAlphanumericString
                (sprintJpa.getSprintNumber(), "s");
        SprintNumber sprintNumber = new SprintNumber(sprintNumberInt);

        SprintId sprintId = new SprintId(projectCode.getCode(), sprintNumber.getSprintNumber());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.UK );
        LocalDate startDate = LocalDate.parse(sprintJpa.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(sprintJpa.getEndDate(), formatter);
        Period period = new Period(startDate, endDate);

        Sprint sprint = factory.createSprint(projectCode, sprintId, sprintNumber
                , period);
        sprint.changeStatus(sprintJpa.getStatus());

        List<UserStoryInSprintJpa> userStoryInSprintJpa =
                sprintJpa.getUserStoriesInSprint();
        for (UserStoryInSprintJpa storyInSprintJpa : userStoryInSprintJpa) {
            String usIdString = storyInSprintJpa.getUsId();
            String[] sprintParts = usIdString.split("_");
            String sprintNumberUs = sprintParts[1];
            String projectCodeUs = sprintParts[0];
            UsId usId = new UsId(projectCodeUs, sprintNumberUs);

            sprint.addUserStory(usId);
        }
        return sprint;
    }
}
