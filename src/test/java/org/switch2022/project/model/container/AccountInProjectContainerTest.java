package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AccountInProjectDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountInProjectContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    Account accountOne;
    AccountDTO accountDTO, accountDTO2;
    ProjectDTO projectDTO;
    AccountInProjectDTO accountInProjectDTOPO, accountInProjectDTOTM, accountInProjectDTOInvalid;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;

    @BeforeEach
    void setUp() {
        // AccountDTOs created.
        accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";
        accountDTO.phoneNumber = 912345678;
        accountDTO.photo = null;

        accountDTO2 = new AccountDTO();
        accountDTO2.name = "Anna";
        accountDTO2.email = "anna@isep.ipp.pt";
        accountDTO2.phoneNumber = 912345679;
        accountDTO2.photo = null;

        // ProjectDTO created.
        Customer customer = new Customer("IT Customer");
        ProjectTypology projectTypology = new ProjectTypology("fixed cost");
        BusinessSector businessSector = new BusinessSector("IT Sector");
        projectDTO = new ProjectDTO("id001", "Test", customer, projectTypology, businessSector);

        // Container of accounts in project created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
    }

    @AfterEach
    void tearDown() {
        accountDTO = null;
        accountDTO2 = null;
        projectDTO = null;
        accountInProjectDTOPO = null;
        accountsInProject.clear();
        accountInProjectContainer = null;
    }

    /**
     * Testing if one is able to add account to project with valid role.
     */
    @Test
    void ensureThatProductOwnerIsAddedToAccountsInProjects() {
        //Assert
        accountInProjectDTOPO = new AccountInProjectDTO();
        accountInProjectDTOPO.accountDTO = accountDTO;
        accountInProjectDTOPO.projectDTO = projectDTO;
        accountInProjectDTOPO.role = "Product Owner";
        accountInProjectDTOPO.costPerHour = 7.5f;
        accountInProjectDTOPO.percentageAllocation = 45.0f;
        accountInProjectDTOPO.startDate = LocalDate.of(2023, 1, 19);
        accountInProjectDTOPO.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountInProjectDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatAccountWithInvalidRoleIsNotAddedToAccountsInProjects() {
        //Assert
        accountInProjectDTOInvalid = new AccountInProjectDTO();
        accountInProjectDTOInvalid.accountDTO = accountDTO;
        accountInProjectDTOInvalid.projectDTO = projectDTO;
        accountInProjectDTOInvalid.role = "Product Visionary";
        accountInProjectDTOInvalid.costPerHour = 7.5f;
        accountInProjectDTOInvalid.percentageAllocation = 45.0f;
        accountInProjectDTOInvalid.startDate = LocalDate.of(2023, 1, 15);
        accountInProjectDTOInvalid.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountInProjectDTOInvalid);
        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTeamMemberIsAddedToAccountsInProjects() {
        //Assert
        accountInProjectDTOTM = new AccountInProjectDTO();
        accountInProjectDTOTM.accountDTO = accountDTO2;
        accountInProjectDTOTM.projectDTO = projectDTO;
        accountInProjectDTOTM.role = "Team Member";
        accountInProjectDTOTM.costPerHour = 6.5f;
        accountInProjectDTOTM.percentageAllocation = 45.0f;
        accountInProjectDTOTM.startDate = LocalDate.of(2023, 1, 19);
        accountInProjectDTOTM.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountInProjectDTOTM);
        //Assert
        assertTrue(result);
    }
    /*
    @Test
    void ensureThatTeamMemberIsAddedToAccountsInProjects() {
        // Arrange
        AccountInProjectDTO dto = new AccountInProjectDTO();
        dto.accountDTO = account2;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = startDate;

        boolean expected = true;
        // Act
        boolean result = accountInProjectContainer.addUserToProject(dto);

        // Assert
        assertEquals(expected, result);
    }*/

    /*@Test
    void ensureThatTeamMemberIsNotAddedToAccountsInProject() {
        // Arrange
        AccountInProjectDTO dto = new AccountInProjectDTO();
        dto.account = account;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = startDate;

        boolean expected = true;
        // Act
        boolean result = accountInProjectContainer.addUserToProject(dto);

        // Assert
        assertNotEquals(expected, result);
    }*/
}