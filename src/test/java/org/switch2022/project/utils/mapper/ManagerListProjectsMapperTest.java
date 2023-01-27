package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ManagerListProjectsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerListProjectsMapperTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */
    Project projectOne, projectTwo;
    ManagerListProjectsDTO projectOneDTO, projectTwoDTO, projectThreeDTO;
    List<Project> projects;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;


    @BeforeEach
    void setUp() {
        projectOne = new Project("AA001", "Aptoide", new Customer("ISEP", "228674498"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
        projectTwo = new Project("AA002", "Aptoide", new Customer("PortoTech", "228674498"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectContainer = new ProjectContainer(projects);

        projectOneDTO = new ManagerListProjectsDTO("AA001", "Aptoide", "ISEP", "Planned", "Fixed cost",
                "fishing");
        projectTwoDTO = new ManagerListProjectsDTO("AA004", "Aptoide", "PortoTech", "Planned", "Fixed cost",
                "Hunting");
        projectThreeDTO = new ManagerListProjectsDTO("AA001", "Aptoide", "ISEP", "Planned", "Fixed cost",
                "fishing");

        company = new Company(null, null, null,
                projectContainer, null, null, null);

    }

    @AfterEach
    void tearDown() {
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectTypologyContainer = null;
        projects.clear();
        projectContainer = null;
        projectOneDTO = null;
        projectTwoDTO = null;
        customerContainer = null;
        company = null;
    }

    /**
     * This test verifies if a list of projects DTO is successfully created from a list of projects
     */

    @Test
    void getListOfProjectsDTO() {
        //Arrange
        List<ManagerListProjectsDTO> expected = new ArrayList<>();
        expected.add(new ManagerListProjectsDTO("AA001", "Aptoide", "isep", "planned",
                "fixed cost", "fishing"));
        expected.add(new ManagerListProjectsDTO("AA002", "Aptoide", "portotech", "planned",
                "fixed cost", "fishing"));
        //Act
        List<ManagerListProjectsDTO> result = ManagerListProjectsMapper.getListOfProjectsDTO(projects);
        //Assert
        assertEquals(expected, result);
    }
}