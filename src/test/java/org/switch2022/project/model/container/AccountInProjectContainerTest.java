package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.AccountInProjectContainer;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountInProjectContainerTest {
    Account account;
    Account account2;
    Customer customer;
    ProjectTypology projectTypology;
    BusinessSector businessSector;
    Project project;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject;

    List<AccountInProject> accountsInProject;

    AccountInProjectContainer accountInProjectContainer;

    @BeforeEach
    void setUp() {
        account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        account2 = new Account("Anna", "anna@isep.ipp.pt", 912345679, null);
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
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
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
    void ensureThatTeamMemberIsAddedToAccountsInProjects() {
        // Arrange
        AllocationDTO dto = new AllocationDTO();
        dto.account = account2;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = startDate;

        boolean expected = true;
        // Act
        boolean result = accountInProjectContainer.addTeamMemberToProject(dto);

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatTeamMemberIsNotAddedToAccountsInProject() {
        // Arrange
        AllocationDTO dto = new AllocationDTO();
        dto.account = account;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = startDate;

        boolean expected = true;
        // Act
        boolean result = accountInProjectContainer.addTeamMemberToProject(dto);

        // Assert
        assertNotEquals(expected,result);
    }
}