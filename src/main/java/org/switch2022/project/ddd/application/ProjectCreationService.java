package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.utils.Utils;

import java.util.Optional;

import static java.lang.Integer.parseInt;

/**
 * Class ProjectCreationService allows to interact with Sprint aggregate.
 */

@Service
public class ProjectCreationService {

    /**
     * Attributes
     */

    @Autowired
    private IFactoryProject factoryProject;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private ITypologyRepository typologyRepository;
    @Autowired
    private IBusinessSectorRepository businessSectorRepository;
    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * This method creates a new Project with the next project code available and adds it to the repository.
     *
     * @param projectCreationDto contains all information needed for creation of a project.
     * @return returns true if the project is created and throws an exception otherwise.
     */
    public String createProject(ProjectCreationDto projectCreationDto) {
        int projectNumber = calculateNextProjectNumber();
        Name projectName = new Name(projectCreationDto.projectName);
        Description projectDescription = new Description(projectCreationDto.projectDescription);

        TaxId customerTaxId = new TaxId(customerRepository.findCustomerTaxIdByName(projectCreationDto.customerName));

        BusinessSectorId businessSectorId = new BusinessSectorId(parseInt(businessSectorRepository.
                getBusinessSectorIdByName(projectCreationDto.businessSectorName)));

        ProjectTypologyId projectTypologyId = new ProjectTypologyId(parseInt(typologyRepository.
                getTypologyIdByName(projectCreationDto.typologyName)));

        Project project = factoryProject.createProject(projectNumber, projectName, projectDescription,
                businessSectorId, customerTaxId, projectTypologyId);

        projectRepository.addProjectToProjectRepository(project);

        return project.getProjectCode();
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
        return projectRepository.findByCode(projectCode);
    }

    /**
     * This method adds a Project to the Repository.
     *
     * @param project to be added to the Repository
     */

    public boolean addProject(Project project) {
        return projectRepository.addProjectToProjectRepository(project);
    }
}
