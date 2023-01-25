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

    Account accountOne, accountTwo, accountThree, accountFour;
    List<Account> accounts;
    Project projectOne, projectTwo;
    Customer customerOne, customerTwo;
    ProjectTypology projectTypologyOne;
    AccountDTO accountDTO, accountDTO2, accountDTO3;
    ProjectDTO projectDTO;
    AllocationDTO accountInProjectDTOPO, accountInProjectDTOTM, accountInProjectDTOInvalid;
    Project project;
    AllocationDTO allocationDTOPO, allocationDTOTM,
            allocationDTOInvalid, allocationDTOAllocationValid,
            allocationDTOAllocationInvalid;
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
        customerOne = new Customer("Genius Software", "228674498");
        customerTwo = new Customer("Delta Software", "228674498");

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
        accountDTO3 = new AccountDTO();
        accountDTO3.name = "Anna";
        accountDTO3.email = "anna@isep.ipp.pt";

        // ProjectDTO created.
        /*customer = new Customer("IT Customer");
        ProjectTypology projectTypology = new ProjectTypology("fixed cost");
        BusinessSector businessSector = new BusinessSector("IT Sector");*/
        projectDTO = new ProjectDTO("id001", "Test", "IT Customer","fixed cost", "IT Sector");

        //Project created
        project = new Project("proj001", "software development management", new Customer(
                "IT Customer","228674498"),new ProjectTypology("fixed cost"), new BusinessSector("IT " +
                "Sector"));

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
        allocationDTOPO = null;
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