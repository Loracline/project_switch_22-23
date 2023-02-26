package org.switch2022.project.dto.mapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;
import org.switch2022.project.dto.ManagerListProjectsDto;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ManagerListProjectsMapperTest {
    /**     * BeforeEach and AfterEach executes common code before/after running the tests below.     */    Project projectOne, projectTwo;
    ManagerListProjectsDto projectOneDTO, projectTwoDTO, projectThreeDTO;
    List<Project> projects;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    @BeforeEach    void setUp() {
        projectOne = new Project("AA001", "Aptoide", new Customer("ISEP", "228674498"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
        projectTwo = new Project("AA002", "Aptoide", new Customer("PortoTech", "228674498"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectContainer = new ProjectContainer();
        projectOneDTO = new ManagerListProjectsDto("AA001", "Aptoide", "ISEP", "Planned", "Fixed cost",
                "fishing");
        projectTwoDTO = new ManagerListProjectsDto("AA004", "Aptoide", "PortoTech", "Planned", "Fixed cost",
                "Hunting");
        projectThreeDTO = new ManagerListProjectsDto("AA001", "Aptoide", "ISEP", "Planned", "Fixed cost",
                "fishing");
        company = new Company(null, null, null,
                projectContainer, null, null, null);
    }
    @AfterEach    void tearDown() {
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
    /**     * This test verifies if a list of projects DTO is successfully created from a list of projects     */
    @Test    void getListOfProjectsDTO() {
        //Arrange
        List<ManagerListProjectsDto> expected = new ArrayList<>();
        expected.add(new ManagerListProjectsDto("AA001", "Aptoide", "isep", "planned",
                "fixed cost", "fishing"));
        expected.add(new ManagerListProjectsDto("AA002", "Aptoide", "portotech", "planned",
                "fixed cost", "fishing"));
        //Act
        List<ManagerListProjectsDto> result = ManagerListProjectsMapper.getListOfProjectsDTO(projects);
        //Assert
        assertEquals(expected, result);
    }
}