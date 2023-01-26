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
    AllocationDTO allocationDTOPO, allocationDTOTM, allocationDTOSM;
    List<AccountInProject> accountsInProject;
    AccountInProject accountInProjectOne, accountInProjectTwo, accountInProjectThree, accountInProjectFour, accountInProjectFive;
    AccountInProjectContainer accountInProjectContainer;


    BusinessSector businessSectorOne;

    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;

    @BeforeEach
    void setUp() {

        //AccountInProject;
        costPerHour = 1;
        percentageAllocation = 1;
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
        accountInProjectTwo = new AccountInProject(accountTwo, projectOne, "Team Member", costPerHour, 50f, startDate);
        accountInProjectThree = new AccountInProject(accountThree, projectOne, "Product Owner", costPerHour, percentageAllocation, startDate);
        accountInProjectFour = new AccountInProject(accountOne, projectTwo, "Scrum Master", costPerHour, percentageAllocation, startDate);
        accountInProjectFour.setEndDate(LocalDate.of(2020, 1, 8));
        accountInProjectFive = new AccountInProject(accountThree, projectTwo, "Team Member", costPerHour, percentageAllocation, startDate);
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);
        accountsInProject.add(accountInProjectFour);
        accountsInProject.add(accountInProjectFive);

        // AccountDTOs created.
        accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";

        accountDTO2 = new AccountDTO();
        accountDTO2.name = "Anna";
        accountDTO2.email = "anna@isep.ipp.pt";

        projectDTO = new ProjectDTO("id001", "Test", "John", "Fixed cost", "Hunting");
        accountDTO3 = new AccountDTO();
        accountDTO3.name = "Anna";
        accountDTO3.email = "anna@isep.ipp.pt";

        // ProjectDTO created.
        projectDTO = new ProjectDTO("id001", "Test", "IT Customer","fixed cost", "IT Sector");

        //container
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
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
        accounts.clear();
        accountInProjectContainer = null;
        businessSectorOne = null;
    }

    /**
     * Testing if one is able to add account to project with an allocationDTO containing valid attributes, including
     * the role as a Product Owner. It should result in a true statement.
     */

    @Test
    void ensureThatProductOwnerIsSuccessfullyAddedToAccountsInProjects() {
        //Arrange
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is able to add account to project with an allocationDTO containing valid attributes, including
     * the role as a Team Member. It should result in a true statement.
     */

    @Test
    void ensureThatTeamMemberIsSuccessfullyAddedToAccountsInProjects() {
        //Arrange
        allocationDTOTM = new AllocationDTO();
        allocationDTOTM.role = "Team Member";
        allocationDTOTM.costPerHour = 7.5f;
        allocationDTOTM.percentageAllocation = 45.0f;
        allocationDTOTM.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOTM);
        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is able to add account to project with an allocationDTO containing valid attributes, including
     * the role as a Scrum Master. It should result in a true statement.
     */

    @Test
    void ensureThatScrumMasterIsSuccessfullyAddedToAccountsInProjects() {
        //Arrange
        allocationDTOSM = new AllocationDTO();
        allocationDTOSM.role = "Scrum Master";
        allocationDTOSM.costPerHour = 7.5f;
        allocationDTOSM.percentageAllocation = 45.0f;
        allocationDTOSM.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOSM);
        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with an allocationDTO containing an
     * invalid role. It should result in a false statement.
     */

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfRoleIsInvalid() {
        //Arrange
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Visionary";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    /**
     * Testing if one is able to add a duplicated AccountInProject, with DTO attributes that are equal to
     * another AccountInProject, with the same account and same project It should result in a false statement.
     */

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfAllocationExists() {
        //Arrange
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Team Member";
        allocationDTOPO.costPerHour = 1f;
        allocationDTOPO.percentageAllocation = 1f;
        allocationDTOPO.startDate = LocalDate.of(2020, 1, 8);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with an invalid sum of percentage of allocation.
     * It should result in a false statement since accountTwo is already allocated to another project with 50%
     * of allocation.
     */

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfPercentageAllocationIsInvalid() {
        //Assert
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 65.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountTwo, projectTwo,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with an invalid sum of percentage of allocation.
     * It should result in a tue statement since accountTwo now is 100% allocated.
     */

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfPercentageAllocationIsInvalidAHundred() {
        //Assert
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 50.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountTwo, projectTwo,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with a null project. It should result in a false statement.
     */

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfProjectIsNull() {
        //Assert
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, null,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with a null account. It should result in a false statement.
     */

    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfAccountIsNull() {
        //Assert
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(null, projectOne,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    /**
     * Testing if a Manager profile is able to retrieve the list of resources allocated in a given project.
     * It should return the list of accounts allocated to that project.
     */

    @Test
    void ensureThatAllAccountsByProjectAreListedSuccessfully() {
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);

        //Act
        List<Account> result = accountInProjectContainer.listAccountsByProject("1A");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if a user that doesn't have a Manager profile is able to retrieve the list of resources allocated in a
     * given project. It should return an empty list.
     */

    @Test
    void ensureThatListAccountsByProjectIsEmpty_NoProject() {
        List<Account> expected = new ArrayList<>();

        //Act
        List<Account> result = accountInProjectContainer.listAccountsByProject("AA0099");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAllProjectsInAccountsAreListedSuccessfully() {
        //Assert
        List<Project> expected = new ArrayList<>();
        expected.add(projectOne);
        expected.add(projectTwo);
        //Act
        List<Project> result = accountInProjectContainer.listProjectsByAccount("mike@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListProjectsInAccountIsEmpty() {
        //Assert
        List<Project> expected = new ArrayList<>();
        //Act
        List<Project> result = accountInProjectContainer.listProjectsByAccount("mary@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }
}