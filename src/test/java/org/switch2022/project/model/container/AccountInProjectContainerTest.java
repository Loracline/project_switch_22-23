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

    Account accountOne, accountTwo, accountThree, accountFour;
    List<Account> accounts;
    Project projectOne, projectTwo;
    Customer customerOne, customerTwo;
    ProjectTypology projectTypologyOne;
    AccountDTO accountDTO, accountDTO2;
    ProjectDTO projectDTO;
    AccountInProjectDTO accountInProjectDTOPO, accountInProjectDTOTM, accountInProjectDTOInvalid;

    List<AccountInProject> accountsInProjectOne;
    AccountInProject accountInProjectOne, accountInProjectTwo, accountInProjectThree, accountInProjectFour, accountInProjectFive;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    AccountInProjectContainer accountInProjectContainerOne;

    BusinessSector businessSectorOne;

    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;

    @BeforeEach
    void setUp() {

        costPerHour = 1;
        percentageAllocation = 1;
        //startDate = LocalDate.now();
        startDate = LocalDate.of(2020, 1, 8);

        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountThree = new Account("Anna", "anna@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);
        accounts.add(accountFour);

        //customer
        customerOne = new Customer("Genius Software");
        customerTwo = new Customer("Delta Software");

        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne);
        projectTwo = new Project("2B", "Software Development Management", customerTwo, projectTypologyOne, businessSectorOne);

        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", costPerHour, percentageAllocation, startDate);
        accountInProjectTwo = new AccountInProject(accountTwo, projectOne, "Team Member", costPerHour, percentageAllocation, startDate);
        accountInProjectThree = new AccountInProject(accountThree, projectOne, "Product Owner", costPerHour, percentageAllocation, startDate);
        accountInProjectFour = new AccountInProject(accountThree, projectTwo, "Scrum Master", costPerHour, percentageAllocation, startDate);
        accountInProjectFive = new AccountInProject(accountOne, projectTwo, "Team Member", costPerHour, percentageAllocation, startDate);
        accountsInProjectOne = new ArrayList<>();
        accountsInProjectOne.add(accountInProjectOne);
        accountsInProjectOne.add(accountInProjectTwo);
        accountsInProjectOne.add(accountInProjectThree);
        accountsInProjectOne.add(accountInProjectFour);
        accountsInProjectOne.add(accountInProjectFive);

        // AccountDTOs created.
        accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";
       // accountDTO.phoneNumber = 912345678;
       // accountDTO.photo = null;

        accountDTO2 = new AccountDTO();
        accountDTO2.name = "Anna";
        accountDTO2.email = "anna@isep.ipp.pt";
        //accountDTO2.phoneNumber = 912345679;
        //accountDTO2.photo = null;

        projectDTO = new ProjectDTO("id001", "Test", "John",
                "Fixed cost", "Hunting");

        // Container of accounts in project created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        //containers
        accountInProjectContainerOne = new AccountInProjectContainer(accountsInProjectOne);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        accountFour = null;
        projectOne = null;
        projectTwo = null;
        customerOne = null;
        customerTwo = null;
        projectTypologyOne = null;
        accountDTO = null;
        accountDTO2 = null;
        projectDTO = null;
        accountInProjectDTOPO = null;
        accountsInProject.clear();
        accountsInProjectOne.clear();
        accounts.clear();
        accountInProjectContainer = null;
        businessSectorOne = null;
        accountInProjectContainerOne = null;
    }

    /**
     * Testing if one is able to add account to project with valid role.
     */
   /* @Test
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
    }*/

   /* @Test
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
    }*/

   /* @Test
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
    }*/
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

    @Test
    void ensureThatAllAccountsByProjectAreListedSuccessfully() {
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);

        //Act
        List<Account> result = accountInProjectContainerOne.listAccountsByProject("1A");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListAccountsByProjectIsEmpty_NoProject() {
        List<Account> expected = new ArrayList<>();

        //Act
        List<Account> result = accountInProjectContainerOne.listAccountsByProject("AA0099");

        //Assert
        assertEquals(expected, result);
    }
}