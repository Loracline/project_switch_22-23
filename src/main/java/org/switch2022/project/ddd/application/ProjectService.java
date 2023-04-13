package org.switch2022.project.ddd.application;


import org.switch2022.project.ddd.domain.model.project.*;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.utils.Utils;
import org.switch2022.project.ddd.utils.Validate;

import java.util.List;
import java.util.Optional;

/**
 * Class ProjectService allows to create and
 * manipulate Project aggregate.
 */
public class ProjectService {
    /**
     * Attributes
     */
    private final IFactoryProject factoryProject;
    private final IProjectRepository projectRepository;
    private final IFactoryProductBacklog factoryProductBacklog;

    /**
     * Constructor.
     *
     * @param factoryProject        it creates a new project;
     * @param projectRepository     it saves all projects;
     * @param factoryProductBacklog it creates a productBacklog for each project;
     */

    public ProjectService(IFactoryProject factoryProject, IProjectRepository projectRepository,
                          IFactoryProductBacklog factoryProductBacklog) {
        Validate.notNull(factoryProject, "Factory Project can't be null");
        this.factoryProject = factoryProject;

        Validate.notNull(projectRepository, "Project Repository can't be null");
        this.projectRepository = projectRepository;

        Validate.notNull(factoryProductBacklog, "Factory ProductBacklog can't be null");
        this.factoryProductBacklog = factoryProductBacklog;
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
                                BusinessSectorId businessSectorId, ProjectTypologyId projectTypologyId)
            throws Exception {

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
     * This method adds a userStoryId to the productBacklog of a project.
     *
     * @param usId        of the userStory.
     * @param projectCode of the project where the user Id will be added.
     * @param priority    that the Id will have in the ProductBacklog.
     * @return true if the ID is sucessfully added. otherwise it will return false.
     * @throws Exception if the priority is out og bounds, if the id is already in the ProductBacklog
     *                   and if the projectCode doesn't match any Project in the repository.
     */

    public boolean addUsToProductBacklog(UsId usId, String projectCode, int priority) throws Exception {

        Optional<Project> projectOptional = getProjectByCode(projectCode);
        Project project;
        if (projectOptional.isPresent()) {
            project = projectOptional.get();
            if (!project.addUserStory(priority, usId)) {
                throw new Exception("The User Story is already in the Product Backlog");
            }
        } else {
            throw new Exception("No project with that code");
        }
        return project.addUserStory(priority, usId);
    }

    /**
     * This method will return an Optional Project from the repository.
     *
     * @param code of the project to be retrieved.
     * @return an optional from the repository.
     */
    public Optional<Project> getProjectByCode(String code) {
        int codeNumber = Utils.getIntFromAlphanumericString(code,"P");
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
}
