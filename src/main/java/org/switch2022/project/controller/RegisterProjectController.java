package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.factories.IFactoryProductBacklog;
import org.switch2022.project.factories.IFactoryProject;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.model.Profile;


/**
 * Class RegisterProjectController is built to register projects.
 */

public class RegisterProjectController {
    private final Company company;

    /**
     * RegisterProjectController constructor
     */
    public RegisterProjectController(Company company) {
        this.company = company;
    }

    /**
     * This method will call the method registerProject in Company
     *
     * @return true if the project is registered
     */
    public boolean registerProject(ProjectCreationDto projectDto, String email, IFactoryProductBacklog factoryProductBacklog,
                                   IFactoryUserStory factoryUserStory, IFactoryProject factoryProject) {
        return company.validateProfileRequired(email, Profile.MANAGER) &&
                company.registerProject(projectDto,factoryProductBacklog,factoryUserStory, factoryProject);
    }
}

