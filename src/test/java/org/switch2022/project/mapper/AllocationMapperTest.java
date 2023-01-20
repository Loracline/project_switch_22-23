package org.switch2022.project.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.DTO.AllocationDTO;
import org.switch2022.project.model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AllocationMapperTest {

    Account account;
    Customer customer;
    ProjectTypology projectTypology;
    BusinessSector businessSector;
    Project project;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject;

    @BeforeEach
    void setUp() {
        account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        customer = new Customer("IT Customer");
        projectTypology = new ProjectTypology("fixed cost");
        businessSector = new BusinessSector("IT Sector");
        project = new Project("1A", "project One", customer, projectTypology,
                businessSector);
        costPerHour = 7.5f;
        percentageAllocation = 45.0f;
        startDate = LocalDate.of(2023,01,19);
        accountInProject = new AccountInProject(account, project,
                costPerHour, percentageAllocation, startDate);
        accountInProject.setRole("team member");
    }

    @AfterEach
    void tearDown() {
        account = null;
        customer = null;
        projectTypology = null;
        businessSector = null;
        project = null;
        accountInProject = null;
    }

    @Test
    void ensuresAccountInProjectTeamMemberIsSuccessfullyCreated() {
        //Arrange
        AllocationDTO dto = new AllocationDTO();
        dto.account = account;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = startDate;
        AllocationMapper mapper = new AllocationMapper();
        AccountInProject expected = accountInProject;

        //Act
        AccountInProject result = mapper.addTeamMemberToProject(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensuresAccountInProjectTeamMemberIsNotCreated() {
        //Arrange
        AllocationDTO dto = new AllocationDTO();
        dto.account = account;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = LocalDate.of(2023,02,19);
        AllocationMapper mapper = new AllocationMapper();
        AccountInProject expected = accountInProject;

        //Act
        AccountInProject result = mapper.addTeamMemberToProject(dto);

        //Assert
        assertNotEquals(expected, result);
    }
}