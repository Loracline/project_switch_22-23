package org.switch2022.project.container;

import org.switch2022.project.dto.*;
import org.switch2022.project.dto.mapper.ProjectCreationMapper;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.SprintBacklog;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class ProjectContainer is built to access and manipulate the set of projects
 * of this company.
 */
public class ProjectContainer {
    /**
     * Attributes
     */
    private final List<Project> projects = new ArrayList<>();

    /**
     * This method verify the existence of a project by code confirmation.
     *
     * @return the project with given code.
     */
    private Optional<Project> getProjectByCode(String code) {
        Project projectRequested = null;
        int i = 0;
        while (i < this.projects.size() && projectRequested == null) {
            if (projects.get(i) != null && projects.get(i).hasProjectCode(code)) {
                projectRequested = projects.get(i);
            }
            i++;
        }
        return Optional.ofNullable(projectRequested);
    }

    /**
     * Getter method for the attribute: projects.
     *
     * @return a list of all the projects in the container.
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * This method creates a new project and adds it to the container if it
     * doesn't already exist.
     *
     * @param projectCreationDto data transfer object of the attributes of project.
     * @return TRUE if registered and FALSE otherwise.
     */
    public boolean registerProject(ProjectCreationDto projectCreationDto,
                                   ProjectTypologyContainer
                                           projectTypologyContainer,
                                   CustomerContainer customerContainer,
                                   BusinessSectorContainer
                                           businessSectorContainer,
                                   IFactoryProductBacklog factoryProductBacklog,
                                   IFactoryUserStory factoryUserStory,
                                   IFactoryProject factoryProject,
                                   IFactoryPeriod iFactoryPeriod,
                                   IFactorySprintBacklog iFactorySprintBacklog,
                                   IFactorySprint iFactorySprint) {
        Project project = ProjectCreationMapper.getProjectFromDto(projectCreationDto,
                projectTypologyContainer,
                customerContainer, businessSectorContainer, factoryProductBacklog,
                factoryUserStory, factoryProject, iFactoryPeriod,
                iFactorySprintBacklog, iFactorySprint);
        return addProjectToProjectContainer(project);
    }

    /**This method adds a Project to Project Container if the Project does not exist.
     *
     * @param project to be added.
     * @return TRUE if the Project was added to Project Container and FALSE otherwise.
     */
    public boolean addProjectToProjectContainer(Project project){
        boolean projectRegistered = false;
        if (!doesProjectExist(project)) {
            projects.add(project);
            projectRegistered = true;
        }
        return projectRegistered;
    }

    /**
     * This method verifies if the project already exists in the container through the
     * project code.
     *
     * @param project existence that shall be verified.
     * @return TRUE if exists, and FALSE otherwise.
     */
    private boolean doesProjectExist(Project project) {
        return projects.contains(project);
    }

    /**
     * This method returns the sprint backlog of the project of interest, in a given date.
     *
     * @param projectCode       of interest.
     * @param date              of interest.
     * @param iFactoryUserStory interface one must use to copy the User Stories
     *                          contained in the Sprint Backlog.
     * @return an Optional object of the Sprint Backlog.
     */
    public Optional<SprintBacklog> getScrumBoard(String projectCode, LocalDate date,
                                                 IFactoryUserStory iFactoryUserStory) {
        Optional<SprintBacklog> sprintBacklogRequested = Optional.empty();
        for (Project project : this.projects) {
            if (project.hasProjectCode(projectCode)) {
                sprintBacklogRequested = project.getSprintBacklogByDate(date,
                        iFactoryUserStory);
            }
        }
        return sprintBacklogRequested;
    }

    /**
     * This method creates an userStory in the requested project
     * returns true if the userStory is successfully created
     */
    public boolean createUserStory(ProjectDto projectDto,
                                   UserStoryCreationDto userStoryCreationDto) {
        boolean isUserStoryCreated = false;
        Optional<Project> projectOptional = getProjectByCode(projectDto.getProjectCode());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            isUserStoryCreated = project.createUserStory(userStoryCreationDto);
        }
        return isUserStoryCreated;
    }

    /**
     * This method sets the effort of a userStory.
     *
     * @param userStoryDto to estimate the effort.
     * @param effort       of the userStory.
     * @param projectCode  code of the project.
     * @return true if the effort is set and false otherwise.
     */
    public boolean estimateEffortUserStory(UserStoryDto userStoryDto, Effort effort,
                                           String projectCode, LocalDate date) {
        boolean isEffortSet = false;
        Optional<Project> projectOptional = getProjectByCode(projectCode);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            isEffortSet = project.estimateEffortUserStory(userStoryDto, effort, date);
        }
        return isEffortSet;
    }

    /**
     * This method should return the Product Backlog of a given Project.
     */
    public ProductBacklog getProductBacklog(ProjectDto projectDto) {
        Optional<Project> projectOptional = getProjectByCode(projectDto.code);
        Project project = projectOptional.get();
        return project.getProductBacklog();
    }

    /**
     * This method creates a Sprint in the requested project.
     * returns true if the Sprint is successfully created.
     */
    public boolean createSprint(SprintCreationDto sprintCreationDto, ProjectDto projectDto) {
        boolean isSprintCreated = false;
        Optional<Project> projectOptional = getProjectByCode(projectDto.code);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            isSprintCreated = project.createSprint(sprintCreationDto);
        }
        return isSprintCreated;
    }

    /**
     * This method adds a User Story to Sprint Backlog if the Project exists.
     *
     * @param projectCode of the project one searches for.
     * @param userStoryNumber of the user story to be added.
     * @param sprintNumber of the Sprint that contains the sprint backlog.
     * @return TRUE if the User Story was added to Sprint Backlog and FALSE otherwise.
     */
    public boolean addUserStoryToSprintBacklog(String projectCode, String userStoryNumber,
                                               String sprintNumber) {
        boolean result = false;
        Optional<Project> projectOptional = getProjectByCode(projectCode);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            result = project.addUserStoryToSprintBacklog(userStoryNumber, sprintNumber);
        }
        return result;
    }
}