package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.factories.*;
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
                                   IFactoryUserStory factoryUserStory, IFactoryProject factoryProject, IFactoryPeriod iFactoryPeriod,
                                   IFactorySprintBacklog iFactorySprintBacklog, IFactorySprint iFactorySprint) {
        return company.validateProfileRequired(email, Profile.MANAGER) &&
                company.registerProject(projectDto,factoryProductBacklog,factoryUserStory, factoryProject, iFactoryPeriod,
                         iFactorySprintBacklog, iFactorySprint);
    }
}

