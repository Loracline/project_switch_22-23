package org.switch2022.project.ddd.application;

import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.project.IFactoryProductBacklog;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class ProjectService allows to create and
 * manipulate Project aggregate.
 */
@Service
public class ProjectService {
    /**
     * Attributes
     */
    @Autowired
    private IFactoryProject factoryProject;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IFactoryProductBacklog factoryProductBacklog;
    @Autowired
    private IUsRepository usRepository;

    /**
     * Constructor.
     */

    public ProjectService() {
    }

    /**
     * This method creates a new Project and adds it to the repository, and creates a ProjectCode
     * using the projectRepository.
     *
     * @param projectCreationDto it has all the information needed to create a project
     * @param customerId         the identifier of the customer
     * @param businessSectorId   the identifier of the businessSector
     * @param projectTypologyId  the identifier of the projectTypology
     */


    public String createProject(ProjectCreationDto projectCreationDto, CustomerId customerId,
                                BusinessSectorId businessSectorId,
                                ProjectTypologyId projectTypologyId) {

        int projectNumber = calculateNextProjectNumber();
        Code code = new Code(projectNumber);
        Project project = factoryProject.createProject(code, projectCreationDto, businessSectorId, customerId,
                projectTypologyId, factoryProductBacklog);
        projectRepository.addProjectToProjectRepository(project);
        return code.getCode();
    }

    /**
     * This method adds a Project to the Repository.
     *
     * @param project to be added to the Repository
     */

    public boolean addProject(Project project) {
        return projectRepository.addProjectToProjectRepository(project);
    }

    /**
     * This method calculates the number of project to include in the project code using the repository size.
     */
    public int calculateNextProjectNumber() {
        return projectRepository.getProjectNumber() + 1;
    }


    /**
     * This method will return an Optional Project from the repository.
     *
     * @param code of the project to be retrieved.
     * @return an optional from the repository.
     */
    public Optional<Project> getProjectByCode(String code) {
        int codeNumber = Utils.getIntFromAlphanumericString(code, "P");
        Code projectCode = new Code(codeNumber);
        return projectRepository.getProjectByCode(projectCode);
    }

    /**
     * This method returns a list of UsID, from the ProductBacklog of a Project
     *
     * @param code of the Project requested
     * @return a list of userStories ID
     * @throws Exception if the projectCode doesn't match any Project in the repository.
     */

    public List<UsId> getProductBacklog(String code) throws Exception {
        Optional<Project> projectOptional = getProjectByCode(code);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            return project.getProductBacklog();
        } else {
            throw new Exception("No project with that code");
        }
    }

    /**
     * Requests a list of userStories with the status planned.
     *
     * @param usId ID of the userStory.
     * @return a list of all userStoriesDto with the status planned.
     */

    public List<UserStory> requestAllPlannedUserStories(List<UsId> usId) {
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usId);
        List<UserStory> userStoriesPlanned = new ArrayList<>();
        if (!userStories.isEmpty()) {
            for (UserStory userStory : userStories) {
                if (userStory.hasStatus(Status.PLANNED)) {
                    userStoriesPlanned.add(userStory);
                }
            }
        }
        return userStoriesPlanned;
    }
}
