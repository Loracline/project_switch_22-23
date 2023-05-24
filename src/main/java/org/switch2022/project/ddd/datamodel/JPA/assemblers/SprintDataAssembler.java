package org.switch2022.project.ddd.datamodel.JPA.assemblers;

import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel.JPA.SprintJpa;
import org.switch2022.project.ddd.datamodel.JPA.UserStoryInSprintJpa;
import org.switch2022.project.ddd.domain.model.sprint.ISprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.domain.value_object.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Assembler class responsible for converting Sprint instances to SprintJpa instances
 * and vice versa.
 */
@Service
public class SprintDataAssembler {
    /**
     * Converts a Sprint instance to a SprintJpa instance.
     *
     * @param sprint The Sprint instance to be converted.
     * @return The converted SprintJpa instance.
     */
    public SprintJpa toData(Sprint sprint) {
        SprintJpa sprintJpa = new SprintJpa(sprint.getSprintId(),
                sprint.getFullSprintNumber(), sprint.getProjectCode(),
                sprint.getStartDate(), sprint.getEndDate());
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

    public Sprint toDomain (SprintJpa sprintJpa) {
        ISprintFactory iSprintFactory = new SprintFactory();

        String[] projectParts = sprintJpa.getProjectCode().split("P", -2);
        int projectNumber = Integer.parseInt(projectParts[0]);
        Code projectCode = new Code(projectNumber);

        String[] sprintIdParts = sprintJpa.getSprintId().split("_", -2);
        String projectCodeId = sprintIdParts[0];
        String sprintNumberId = sprintIdParts[1];
        SprintId sprintId = new SprintId(projectCodeId, sprintNumberId);

        String[] sprintNumberParts = sprintJpa.getSprintNumber().split("S",-2);
        Integer sprintNumberInt = Integer.parseInt(sprintNumberParts[0]);
        SprintNumber sprintNumber = new SprintNumber(sprintNumberInt);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.UK );
        LocalDate startDate = LocalDate.parse(sprintJpa.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(sprintJpa.getEndDate(), formatter);
        Period period = new Period(startDate, endDate);

        Sprint sprint = iSprintFactory.createSprint(projectCode, sprintId, sprintNumber
                , period);

        List<UserStoryInSprintJpa> userStoryInSprintJpa =
                sprintJpa.getUserStoriesInSprint();
        for (UserStoryInSprintJpa storyInSprintJpa : userStoryInSprintJpa) {
            String usIdString = storyInSprintJpa.getUsId();
            String[] sprintParts = usIdString.split("_");
            String sprintNumberUs = sprintParts[1];
            String projectCodeUs = sprintParts[0];
            UsId usId = new UsId(projectCodeUs, sprintNumberUs);

            int effortInt = storyInSprintJpa.getEffort();
            sprint.addUserStory(usId, effortInt);
        }
        return sprint;
    }
}
