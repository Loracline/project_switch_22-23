package org.switch2022.project.ddd.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.sprint.ISprintFactory;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.ProjectNotFoundException;
import org.switch2022.project.ddd.utils.Utils;
import org.switch2022.project.ddd.utils.Validate;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CreateSprintService {

    /**
     * Attributes
     */

    @Autowired
    private ISprintRepository sprintRepository;
    @Autowired
    private ISprintFactory sprintFactory;
    @Autowired
    @Qualifier ("project_jpa")
    private IProjectRepository projectRepository;

    /**
     * This method will create a sprint
     * First it creates all the value objects needed for the creation fo the sprint.
     * After checks if the project exists in the repository and if it has a status
     * different of Planned or closed.
     * Therefore, it gets the project duration and creates a sprint.
     * The sprint is verified if it period is valid: checks if the startDate is after or equal the project start date,
     * if the end date is before or equal the project end date and check if the period is not overlapping with other
     * sprints period.
     * After it adds the sprint to the sprint repository
     *
     * @param projectCode of the project
     * @param startDate   of the sprint
     * @return a String of the stringID
     * @throws Exception if the sprint is already in the repository
     */

    public String createSprint(String projectCode, String startDate) throws Exception {
        Validate.notNullOrEmptyOrBlank(startDate, "sprint Start Date");
        Validate.notNullOrEmptyOrBlank(projectCode, "project code");
        Code code = new Code(Utils.getIntFromAlphanumericString(projectCode, "p"));
        SprintNumber sprintNumber = new SprintNumber(sprintRepository.count() + 1);
        SprintId sprintId = new SprintId(projectCode, sprintNumber.getSprintNumber());
        String sprintIdToBeReturned = "";
        Project project = getProjectByCode(projectCode);
        if (isProjectInStatus(project) == 1) {
            Period period = new Period(LocalDate.parse(startDate), getSprintDuration(project).getSprintDuration());
            Sprint sprint = sprintFactory.createSprint(code, sprintId, sprintNumber, period);
            if (verifyIfPeriodIsValid(project, sprint) == 1 &&
                    sprintRepository.save(sprint)) {
                sprintIdToBeReturned = sprintId.getSprintId();
            } else {
                throw new AlreadyExistsInRepoException("The sprint already exists");
            }
        }
        return sprintIdToBeReturned;
    }

    /**
     * This method will return an Optional Project from the repository.
     *
     * @param code of the project to be retrieved.
     * @return a project from the repository.
     */
    private Project getProjectByCode(String code) {
        Project project;
        int codeNumber = Utils.getIntFromAlphanumericString(code, "p");
        Code projectCode = new Code(codeNumber);
        Optional<Project> projectOptional = projectRepository.findByCode(projectCode);
        if (projectOptional.isPresent()) {
            project = projectOptional.get();
        } else {
            throw new ProjectNotFoundException("No project with that code");
        }
        return project;
    }

    /**
     * This method checks if the sprint start date is after or equal the project start date
     *
     * @param project of the project
     * @param sprint  that was created
     * @return true if the sprint start date is after or equal the project start date
     */

    private static boolean isSprintPeriodAfterProjectStartDate(Project project, Sprint sprint) {
        return sprint.isPeriodAfterOrEqualThanDate(LocalDate.parse(project.getStartDate()));
    }

    /**
     * This method checks if the sprint end date is before or equal the project end date
     *
     * @param project of the project
     * @param sprint  that was created
     * @return true if the sprint end date is before or equal the project end date
     */
    private static boolean isSprintEndDateBeforeProjectEndDate(Project project, Sprint sprint) {
        return sprint.isEndDateBeforeOrGreaterThanDate(LocalDate.parse(project.getEndDate()));
    }

    /**
     * This method verifies if the project is not in status planned or closed
     *
     * @param project to be checked
     * @return returns int 1 is the project is suitable to create sprint
     * @throws Exception if the project is unsuitable to create a sprint
     */
    private static int isProjectInStatus(Project project) throws Exception {
        int result;
        if (!project.hasStatus(ProjectStatus.PLANNED) && !project.hasStatus(ProjectStatus.CLOSED)) {
            result = 1;
        } else {
            throw new Exception("The project status is unsuitable to create sprints");
        }
        return result;
    }

    /**
     * This method verifies if the sprint period does not overlap with other sprints periods of the same project
     *
     * @param sprints list of sprints in the same Project
     * @param sprint  to be added to project
     * @return true if period does not overlap
     */
    private static boolean isSprintPeriodValid(List<Sprint> sprints, Sprint sprint) {
        boolean isSprintPeriodNotOverlapping = false;
        if (sprints.isEmpty()) {
            isSprintPeriodNotOverlapping = true;
        } else {
            Iterator<Sprint> sprintIterator = sprints.iterator();
            while (sprintIterator.hasNext() && !isSprintPeriodNotOverlapping) {
                Sprint s = sprintIterator.next();
                isSprintPeriodNotOverlapping = s.isPeriodNotOverlapping(sprint);
            }
        }
        return isSprintPeriodNotOverlapping;
    }

    /**
     * This method verifies if the Period of the Sprint is valid by checking the period with the project Period
     * and with the other sprints
     *
     * @param project to be checked
     * @param sprint  that was created
     * @return a 1 if the period is valid for the sprint
     * @throws Exception if the sprint period is overlapping with other sprint,
     *                   if the sprint end date is after the project end date and
     *                   if the sprint start date is before the project start date
     */

    private int verifyIfPeriodIsValid(Project project, Sprint sprint) throws Exception {
        int result;
        Code projectCode = new Code
                (Utils.getIntFromAlphanumericString(project.getProjectCode(), "P"));
        if (isSprintPeriodAfterProjectStartDate(project, sprint)) {
            if (isSprintEndDateBeforeProjectEndDate(project, sprint)) {
                if (isSprintPeriodValid(sprintRepository.findByProjectCode(projectCode), sprint)) {
                    result = 1;
                } else {
                    throw new Exception("The sprint period is overlapping with other sprint");
                }
            } else {
                throw new Exception("The sprint end date is after the project end date");
            }
        } else {
            throw new Exception("The sprint start date is before the project start date");
        }
        return result;
    }

    /**
     * This method gets the SprintDuration from the project
     *
     * @param project to be checked
     * @return sprintDuration
     * @throws Exception that the Project doesn't have a sprint duration
     */

    private static SprintDuration getSprintDuration(Project project) throws Exception {
        SprintDuration sprintDuration;
        Optional<SprintDuration> optionalSprintDuration = project.getSprintDuration();
        if (optionalSprintDuration.isPresent()) {
            sprintDuration = optionalSprintDuration.get();
        } else {
            throw (new Exception("No Sprint Duration in this Project"));
        }
        return sprintDuration;
    }

}
