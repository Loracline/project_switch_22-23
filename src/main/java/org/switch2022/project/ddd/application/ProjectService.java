package org.switch2022.project.ddd.application;

import org.switch2022.project.ddd.domain.model.project.*;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.ProjectNotFoundException;
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
     * This method creates a new Project with the next project code available and adds it to the repository.
     *
     * @param description           the description of the project.
     * @param businessSectorId      the identifier of the businessSector.
     * @param customerId            the identifier of the customer.
     * @param projectTypologyId     the identifier of the projectTypology.
     *
     * @return returns the code of the created Project.
     */
    public String createProject(Name projectName, Description description, BusinessSectorId businessSectorId,
                                     CustomerId customerId, ProjectTypologyId projectTypologyId) {
        int projectNumber = calculateNextProjectNumber();
        Code projectCode = new Code(projectNumber);
        ProductBacklog productBacklog = factoryProductBacklog.createProductBacklog(projectCode.getCode());
        Project project = factoryProject.createProject(projectNumber, projectName, description, businessSectorId,
                customerId, projectTypologyId, productBacklog);
        projectRepository.addProjectToProjectRepository(project);
        return project.getProjectCode();
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

    public List<UserStory> getProductBacklog(String code) {
        Optional<Project> projectOptional = getProjectByCode(code);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            List<UsId> productBacklog = project.getProductBacklog();
            return requestAllPlannedUserStories(productBacklog);
        } else {
            throw new ProjectNotFoundException("No project with that code");
        }
    }

    /**
     * Requests a list of userStories with the status planned.
     *
     * @param usIds ID of the userStory.
     * @return a list of all userStoriesDto with the status planned.
     */

    public List<UserStory> requestAllPlannedUserStories(List<UsId> usIds) {
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usIds);
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
