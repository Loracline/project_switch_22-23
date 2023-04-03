package org.switch2022.project.dto.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.dto.ProjectCreationDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectCreationMapperTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    Project projectOne;
    ProjectCreationDto projectCreationDTO;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectTypology typology;
    List<Project> projects;
    BusinessSectorContainer businessSectorContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    BusinessSector businessSector;
    Customer customer;
    Company company;

    IFactoryProject factoryProject;
    IFactoryUserStory factoryUserStory;
    IFactoryProductBacklog factoryProductBacklog;
    IFactoryPeriod factoryPeriod;
    IFactorySprintBacklog factorySprintBacklog;
    IFactorySprint factorySprint;
    IFactoryCustomer iFactoryCustomer;

    @BeforeEach
    void setUp() {
        //Interfaces implemented

        factoryProductBacklog = new FactoryProductBacklog();
        factoryProject = new FactoryProject();
        factoryUserStory = new FactoryUserStory();
        iFactoryCustomer = new FactoryCustomer();
        projectOne = new Project("AA001", "Aptoide",
                new Customer("ISEP", "228674498"),
                new ProjectTypology("Fixed Cost"),
                new BusinessSector("fishing"));
        projects = new ArrayList<>();
        projects.add(projectOne);
        projectContainer = new ProjectContainer();

        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed cost");
        typology = new ProjectTypology("Fixed cost");

        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("ISEP", "228674498", iFactoryCustomer);
        customer = new Customer("ISEP", "228674498");

        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");
        businessSector = new BusinessSector("fishing");

        projectCreationDTO = new ProjectCreationDto("AA001",
                "Aptoide", "ISEP", "228674498",
                "Fixed cost", "fishing");
    }

    @AfterEach
    void tearDown() {
        projectOne = null;
        businessSectorContainer = null;
        businessSector = null;
        projectTypologyContainer = null;
        typology = null;
        projects.clear();
        projectContainer = null;
        projectCreationDTO = null;
        customerContainer = null;
        customer = null;
        company = null;
    }

    @Test
    void ensureThatAccountIsConvertedIntoAccountDTO() {
        // ARRANGE
        Project expected = projectOne;
        // ACT
        Project result = ProjectCreationMapper.getProjectFromDto(projectCreationDTO, projectTypologyContainer,
                customerContainer, businessSectorContainer,factoryProductBacklog,factoryUserStory,factoryProject, factoryPeriod, factorySprintBacklog,factorySprint);
        // ASSERT
        assertEquals(expected, result);
    }
}