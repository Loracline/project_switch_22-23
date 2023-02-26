package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountInProjectContainer;
import org.switch2022.project.model.*;
import org.switch2022.project.dto.AccountDTO;
import org.switch2022.project.dto.AllocationDto;
import org.switch2022.project.dto.ProjectDtoAsManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountInProjectContainerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour, accountFive,
            accountSix;
    AccountDTO accountDTO, accountDTO2, accountDTO3;
    AccountInProject accountInProjectOne, accountInProjectTwo,
            accountInProjectThree, accountInProjectFour, accountInProjectFive,
            accountInProjectSix, accountInProjectSeven, accountInProjectEight,
            accountInProjectNine;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    AllocationDto allocationDTOPO, allocationDTOTM, allocationDTOSM;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    LocalDate endDateOne, endDateTwo;
    Project projectOne, projectTwo;
    ProjectDtoAsManager projectDTOAsManager;
    Customer customerOne, customerTwo;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;

    @BeforeEach
    void setUp() {

        //AccountInProject;
        costPerHour = 1;
        percentageAllocation = 1;
        startDate = LocalDate.of(2020, 1, 8);
        endDateOne = LocalDate.of(2020, 1, 9);
        endDateTwo = LocalDate.of(2024, 1, 9);

        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountThree = new Account("Anna", "anna@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);
        accountFive = new Account("John", "john@isep.ipp.pt", 951456789, null);
        accountSix = new Account("Joseph", "joseph@isep.ipp.pt", 256887456, null);

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
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", costPerHour, percentageAllocation, startDate, endDateOne);
        accountInProjectTwo = new AccountInProject(accountTwo, projectOne, "Team Member", costPerHour, 50f, startDate, endDateTwo);
        accountInProjectThree = new AccountInProject(accountThree, projectOne, "Product Owner", costPerHour, percentageAllocation, startDate, endDateOne);
        accountInProjectFour = new AccountInProject(accountOne, projectTwo, "Scrum Master", costPerHour, percentageAllocation, startDate, endDateOne);
        accountInProjectFive = new AccountInProject(accountThree, projectTwo, "Team Member", costPerHour, percentageAllocation, startDate, endDateOne);
        accountInProjectSix = new AccountInProject(accountThree, projectTwo);
        accountInProjectSeven = new AccountInProject(accountOne, projectOne, "Team Member", costPerHour, percentageAllocation, startDate, endDateTwo);
        accountInProjectEight = new AccountInProject(accountFive, projectTwo, "Team Member", costPerHour, 100f, startDate, endDateTwo);
        accountInProjectNine = new AccountInProject(accountSix, projectTwo);

        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);
        accountsInProject.add(accountInProjectFour);
        accountsInProject.add(accountInProjectFive);
        accountsInProject.add(accountInProjectSix);
        accountsInProject.add(accountInProjectSeven);
        accountsInProject.add(accountInProjectEight);

        // accountDTOs
        accountDTO = new AccountDTO("John", "john@isep.ipp.pt", true);
        accountDTO2 = new AccountDTO("Anna", "anna@isep.ipp.pt", true);
        accountDTO3 = new AccountDTO("Anna", "anna@isep.ipp.pt", true);

        // projectDTO
        projectDTOAsManager = new ProjectDtoAsManager("id001", "Test", "IT Customer", "228674498", "fixed cost", "IT Sector");

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
        accountDTO3 = null;
        projectDTOAsManager = null;
        allocationDTOPO = null;
        allocationDTOTM = null;
        allocationDTOSM = null;
        accountsInProject.clear();
        /*accounts.clear();*/
        accountInProjectContainer = null;
        accountInProjectOne = null;
        accountInProjectTwo = null;
        accountInProjectThree = null;
        accountInProjectFour = null;
        accountInProjectFive = null;
        businessSectorOne = null;
        startDate = null;
    }

    /**
     * Testing if one is able to add account to project with an allocationDTO containing valid attributes, including
     * the role as a Product Owner. It should result in a true statement.
     */
    @Test
    void ensureThatProductOwnerIsAddedToAccountsInProjects() {
        //Assert
        allocationDTOPO = new AllocationDto("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);

        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is not able to add an account to a project with an allocationDTO
     * containing the role Product Owner, if there is already a product owner during
     * the same period. It should result in a false statement.
     */
    @Test
    void ensureThatProductOwnerIsNotAddedToAccountsInProjectsIfAlreadyExistsOne() {
        //Assert
        allocationDTOPO = new AllocationDto("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));
        accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);

        //Assert
        assertFalse(result);
    }

    /**
     * Testing if one is able to add account to project with an allocationDTO containing valid attributes, including
     * the role as a Team Member. It should result in a true statement.
     */
    @Test
    void ensureThatTeamMemberIsSuccessfullyAddedToAccountsInProjects() {
        //Arrange
        allocationDTOTM = new AllocationDto("Team Member", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));

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
        allocationDTOSM = new AllocationDto("Scrum Master", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOSM);

        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is not able to add an account to a project with an allocationDTO
     * containing the role Scrum Master, if there is already a Scrum Master during
     * the same period. It should result in a false statement.
     */
    @Test
    void ensureThatScrumMasterIsNotAddedToAccountsInProjectsIfAlreadyExistsOne() {
        //Assert
        allocationDTOPO = new AllocationDto("Scrum Master", 7.5f, 45.0f, LocalDate.of(2023
                , 1, 19), LocalDate.of(2024, 1, 19));
        accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountOne, projectOne,
                allocationDTOPO);

        //Assert
        assertFalse(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with an allocationDTO containing an
     * invalid role. It should result in a false statement.
     */
    @Test
    void ensureThatAccountIsNotAddedToAccountsInProjectsIfRoleIsInvalid() {
        //Arrange
        allocationDTOPO = new AllocationDto("Product Visionary", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), null);

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
        allocationDTOPO = new AllocationDto("Team Member", 1f, 1f, LocalDate.of(2020, 1, 8), LocalDate.of(2021, 1, 8));

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
        allocationDTOPO = new AllocationDto("Scrum Master", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountTwo, projectTwo,
                allocationDTOPO);

        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is able to add an AccountInProject with an invalid sum of percentage of allocation.
     * It should result in a tue statement since accountTwo now is 100% allocated.
     */
    @Test
    void ensureThatAccountIsAddedToAccountsInProjectsIfPercentageAllocationTotalsOneHundred() {
        //Assert
        allocationDTOPO = new AllocationDto("Product Owner", 7.5f, 50.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));

        //Act
        boolean result = accountInProjectContainer.addUserToProject(accountTwo, projectTwo,
                allocationDTOPO);

        //Assert
        assertTrue(result);
    }

    /**
     * Testing if one is able to add an AccountInProject in case of current sum of percentage of allocation equals zero.
     */
    @Test
    void ensureUserIsAddedToProjectWhenCurrentPercentAllocationIsNull() {
        // Arrange
        allocationDTOPO = new AllocationDto("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));
        boolean expected = true;

        // Act
        boolean result = accountInProjectContainer.addUserToProject(accountFour, projectOne, allocationDTOPO);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if a Manager profile is able to retrieve the list of resources allocated in a given project.
     * It should return the list of accounts allocated to that project.
     */
    @Test
    void ensureThatAllAccountsByProjectAreListedSuccessfully() {
        //Arrange
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);
        expected.add(accountOne);

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
        //Arrange
        List<Account> expected = new ArrayList<>();

        //Act
        List<Account> result = accountInProjectContainer.listAccountsByProject("AA0099");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to list all projects from an account is listed. Should return a list of projects where
     * that account works in.
     */
    @Test
    void ensureThatAllProjectsInAccountsAreListedSuccessfully() {
        //Arrange
        List<Project> expected = new ArrayList<>();
        expected.add(projectOne);
        expected.add(projectTwo);
        expected.add(projectOne);

        //Act
        List<Project> result = accountInProjectContainer.listProjectsByAccount("mike@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to list all projects from an account that has no projects is listed. Should return an empty list.
     */
    @Test
    void ensureThatListProjectsInAccountIsEmpty() {
        //Assert
        List<Project> expected = new ArrayList<>();

        //Act
        List<Project> result = accountInProjectContainer.listProjectsByAccount("mary@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to calculate the percentage of allocation when the account has already left some project.
     */
    @Test
    void calculateCurrentPercentAllocation_EndDateNotNull() {
        // Arrange
        float expected = 1f;

        // Act
        float result = accountInProjectContainer.currentPercentageOfAllocation(accountOne);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureUserIsAddedToProjectSuccessfullyWithMandatoryArgumentsOnly() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = accountInProjectContainer.addUserToProject(accountTwo, projectTwo);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureUserIsNotAddedToProjectWithMandatoryArgumentsOnlyBecauseAllocationAlreadyExists() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProjectContainer.addUserToProject(accountThree, projectTwo);

        // Assert
        assertEquals(expected, result);
    }


    /*
      METHOD isTotalPercentageOfAllocationValid(account, newPercentageAllocation)

      Verifying if the sum of the current percentage of allocation of an account
      plus the new percentage of allocation one is trying to assign does not
      exceed the 100%.
     */

    /**
     * Scenario 1: Total percentage of allocation is valid because it does not
     * exceed the 100%.
     */
    @Test
    void ensurePercentAllocationIsValid_TotalUnderOneHundred() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountTwo, 40);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Total percentage of allocation is valid because it equals
     * 100%.
     */
    @Test
    void ensurePercentAllocationIsValid_TotalEqualsOneHundred() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountTwo, 50);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Total percentage of allocation is invalid because the new
     * percentage of allocation is not valid as it exceeds the 100%, which means
     * that the total will also exceed.
     */
    @Test
    void ensurePercentAllocationIsInvalid_NewPercentAllocationAboveOneHundred() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountTwo, 151);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Total percentage of allocation is invalid because new
     * percentage of allocation is a negative number.
     */
    @Test
    void ensurePercentAllocationIsInvalid_NewPercentAllocationNegative() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountTwo, -1);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Total percentage of allocation is invalid because new
     * percentage of allocation is zero, so there is no new allocation ongoing.
     */
    @Test
    void ensurePercentAllocationIsInvalid_NewPercentAllocationZero() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountTwo, 0);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 6: Total percentage of allocation is invalid because current
     * percentage of allocation is already of 100%, so it is not possible to
     * assign the account to other projects at the moment.
     */
    @Test
    void ensurePercentAllocationIsInvalid_CurrentPercentIsAlreadyMax() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountFive, 50);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 7: New percentage of allocation and current percentage of
     * allocation are both valid, but the sum exceeds 100%.
     */
    @Test
    void ensurePercentAllocationIsInvalid_TotalPercentAllocationAboveOneHundred() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountTwo, 60);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 8: New percentage of allocation equals 100% and the account has
     * no current percentage of allocation.
     */
    @Test
    void ensurePercentAllocationIsValid_CurrentPercentAllocationIsZero() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = accountInProjectContainer.isTotalPercentageOfAllocationValid(accountSix, 100);

        // Assert
        assertEquals(expected, result);
    }
}