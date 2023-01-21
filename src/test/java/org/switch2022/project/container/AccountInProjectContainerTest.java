package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.DTO.AccountInProjectDTO;
import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.controller.AccountDTO;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountInProjectContainerTest {
    AccountDTO accountDTO, accountDTO2;
    ProjectDTO projectDTO;
    AccountInProjectDTO accountInProjectDTOPO,
            accountInProjectDTOInvalid;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;

    @BeforeEach
    void setUp() {
        //set up accounts
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

        //set up projects
        projectDTO = new ProjectDTO();
        projectDTO.customer = new Customer("IT Customer");
        projectDTO.code = "id001";
        projectDTO.projectTypology = new ProjectTypology("fixed cost");
        ;
        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");

        //set up accounts in project list
        accountsInProject = new ArrayList<>();

        //set up account in project container
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

    @Test
    void ensureThatProductOwnerIsAddedToAccountsInProjects() {
        //Assert
        accountInProjectDTOPO = new AccountInProjectDTO();
        accountInProjectDTOPO.accountDTO = accountDTO;
        accountInProjectDTOPO.projectDTO = projectDTO;
        accountInProjectDTOPO.role = "Product Owner";
        accountInProjectDTOPO.costPerHour = 7.5f;
        accountInProjectDTOPO.percentageAllocation = 45.0f;
        accountInProjectDTOPO.startDate = LocalDate.of(2023, 01, 19);
        accountInProjectDTOPO.endDate = LocalDate.of(2023, 01, 22);
        //Act
        boolean result =
                accountInProjectContainer.addUserToProject(accountInProjectDTOPO);
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
        accountInProjectDTOInvalid.startDate = LocalDate.of(2023, 01, 15);
        accountInProjectDTOInvalid.endDate = LocalDate.of(2023, 01, 22);
        //Act
        boolean result =
                accountInProjectContainer.addUserToProject(accountInProjectDTOInvalid);
        //Assert
        assertFalse(result);
    }


    /*@Test
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