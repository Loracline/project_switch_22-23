package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
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
    AccountDTO /*accountDTO, accountDTO2,*/ accountDTO3;
    ProjectDTO projectDTO;
    Project project;
    AllocationDTO allocationDTOPO, allocationDTOTM,
            allocationDTOInvalid, allocationDTOAllocationValid,
            allocationDTOAllocationInvalid;
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

        projectDTO = new ProjectDTO("id001", "Test", "John",
                "Fixed cost", "Hunting");
        accountDTO3 = new AccountDTO();
        accountDTO3.name = "Anna";
        accountDTO3.email = "anna@isep.ipp.pt";
        accountDTO3.phoneNumber = 912345679;
        accountDTO3.photo = null;

        // ProjectDTO created.
        /*customer = new Customer("IT Customer");
        ProjectTypology projectTypology = new ProjectTypology("fixed cost");
        BusinessSector businessSector = new BusinessSector("IT Sector");*/
        projectDTO = new ProjectDTO("id001", "Test", "IT Customer","fixed cost", "IT Sector");

        //Project created
        project = new Project("proj001", "software development management", "IT Customer","fixed cost", "IT Sector");

        // Container of accounts in project created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
    }

    @AfterEach
    void tearDown() {
        accountDTO = null;
        accountDTO2 = null;
        projectDTO = null;
        allocationDTOPO = null;
        accountsInProject.clear();
        accountInProjectContainer = null;
    }

    /**
     * Testing if one is able to add account to project with valid role.
     */
    @Test
    void ensureThatProductOwnerIsAddedToAccountsInProjects() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTeamMemberIsAddedToAccountsInProjects() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Team Member";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatScrumMasterIsAddedToAccountsInProjects() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Scrum Master";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfRoleIsInvalid() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Visionary";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfAllocationExists() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        AccountInProject accountInProject = new AccountInProject(account, project,
                allocationDTOPO.role, allocationDTOPO.costPerHour,
                allocationDTOPO.percentageAllocation, allocationDTOPO.startDate);
        accountsInProject.add(accountInProject);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfPercentageAllocationIsInvalid() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        AccountInProject accountInProject = new AccountInProject(account, project,
                allocationDTOPO.role, allocationDTOPO.costPerHour,
                60f, allocationDTOPO.startDate);
        accountsInProject.add(accountInProject);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }
}