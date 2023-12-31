package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCodeStringDto;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.utils.Utils;

import java.util.Optional;

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
    @Qualifier("project_jpa")
    private IProjectRepository projectRepository;
    @Qualifier("typology_jpa")
    @Autowired
    private ITypologyRepository typologyRepository;
    @Autowired
    @Qualifier("businessSector_jpa")
    private IBusinessSectorRepository businessSectorRepository;
    @Autowired
    @Qualifier("customer_jpa")
    private ICustomerRepository customerRepository;

    /**
     * This method creates a new Project with the next project code available and adds
     * it to the repository.
     *
     * @param projectCreationDto contains all information needed for creation of a
     *                           project.
     * @return returns true if the project is created and throws an exception otherwise.
     */
    public ProjectCodeStringDto createProject(ProjectCreationDto projectCreationDto) {
        int projectNumber = calculateNextProjectNumber();
        Name projectName = new Name(projectCreationDto.projectName);
        Description projectDescription =
                new Description(projectCreationDto.projectDescription);

        TaxId customerTaxId = new TaxId(projectCreationDto.customerId);

        BusinessSectorId businessSectorId =
                new BusinessSectorId(Utils.getIntFromAlphanumericString(
                        projectCreationDto.businessSectorId, "BS"));

        ProjectTypologyId projectTypologyId =
                new ProjectTypologyId(Utils.getIntFromAlphanumericString(
                        projectCreationDto.typologyId, "PT"));

        Project project = factoryProject.createProject(projectNumber, projectName,
                projectDescription,
                businessSectorId, customerTaxId, projectTypologyId);

        projectRepository.save(project);

        return new ProjectCodeStringDto(project.getProjectCode());
    }

    /**
     * This method calculates the number of project to include in the project code
     * using the repository size.
     *
     * @return the next number.
     */
    public int calculateNextProjectNumber() {
        return projectRepository.count() + 1;
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
     * @param project to be added to the Repository.
     * @return true if the project is created and false otherwise.
     */

    public boolean addProject(Project project) {
        return projectRepository.save(project);
    }
}
