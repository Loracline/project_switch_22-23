package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AccountInProjectTest {
    Account account, otherAccount;
    Project project, otherProject;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    LocalDate endDate;
    AccountInProject accountInProject, accountInProjectIncomplete,
            accountInProjectScrumMaster, accountInProjectProductOwner;

    @BeforeEach
    void setUp() {

        //Account in project created.
        account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        project = new Project("1A", "project code", new Customer("John","228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("IT Sector") );
        costPerHour = 7.5f;
        percentageAllocation = 45.0f;
        startDate = LocalDate.of(2023, 1, 19);
        endDate = LocalDate.of(2024, 1,19);

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        accountInProjectIncomplete = new AccountInProject(account, project);
    }

    @AfterEach
    void tearDown() {
        account = null;
        otherAccount = null;
        project = null;
        otherProject = null;
        accountInProject = null;
        accountInProjectIncomplete = null;
    }

    /**
     * Tests for the equals() method.
     */
    @Test
    void ensureThatSameAccountInProjectEqualsItself() {
        // Arrange
        AccountInProject clone = accountInProject;
        boolean expected = true;
        // Act
        boolean result = accountInProject.equals(clone);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoAccountsInProjectAreEqual() {
        // Arrange
        AccountInProject copy = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);
        boolean expected = true;
        // Act
        boolean result = accountInProject.equals(copy);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoAccountsInProjectAreDifferent() {
        // Arrange
        AccountInProject other = new AccountInProject(account, project, "Product Owner",
                costPerHour, percentageAllocation, startDate, endDate);
        boolean expected = false;
        // Act
        boolean result = accountInProject.equals(other);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountInProjectDoesNotEqualsNull() {
        // Assert
        assertNotNull(accountInProject);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatAccountInProjectDoesNotEqualOtherTypeOfObject() {
        // Arrange
        boolean expected = false;
        // Act
        boolean result = accountInProject.equals(account);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatAccountsInProjectHaveSameHashCode() {
        // ARRANGE
        AccountInProject reference = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);
        AccountInProject other = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatAccountsInProjectHaveDifferentHashCode() {
        // ARRANGE
        AccountInProject reference = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);
        AccountInProject other = new AccountInProject(account, project, "Team Member",
                7.0f, percentageAllocation, startDate, endDate);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Test to verify if the percentage allocation is retrieved
     */
    @Test
    void ensureThatPercentageAllocationIsRetrievedSuccessfully() {
        // Arrange
        float expected = 45.0f;

        // Act
        float result = accountInProject.getPercentageOfAllocation();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to validate if Product Owner as a valid role to an account in project. It should return true.
     */
    @Test
    void ensureThatProductOwnerIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Product Owner", costPerHour, percentageAllocation, startDate, endDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    /**
     * Test to validate if Team Member as a valid role to an account in project. It should return true.
     */
    @Test
    void ensureThatTeamMemberIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Team Member", costPerHour, percentageAllocation, startDate, endDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    /**
     * Test to validate if Scrum Master as a valid role to an account in project. It should return true.
     */
    @Test
    void ensureThatScrumMasterIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Scrum Master", costPerHour, percentageAllocation, startDate, endDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    /**
     * Test to verify in an invalid role could be given to an AccountInProject. It should return false.
     */
    @Test
    void ensureThatAnyOtherRoleIsInvalid() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Product Visionary", costPerHour, percentageAllocation, startDate, endDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertFalse(result);
    }

    /**
     * Test to verify if the endDate is successfully retrieved.
     */
    @Test
    void ensureThatEndDateIsRetrievedSuccessfully() {
        //Arrange
        LocalDate expected = LocalDate.of(2024, 1, 19);
        //Act
        LocalDate result = accountInProject.getEndDate();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify if an account is retrieved by giving a valid project code. To make this it accesses
     * accountInProject to retrieve the account itself. It should return the account.
     */
    @Test
    void ensureThatAccountIsRetrievedSuccessfullyWithProjectCode() {
        //Arrange
        Account expected = account;
        //Act
        Account result = accountInProject.getAccountByProject("1A");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify if an account is retrieved by giving an invalid project code. It should return null.
     */
    @Test
    void ensureThatGetAccountByProjectReturnsNullWithInvalidProjectCode() {
        //Act
        Account result = accountInProject.getAccountByProject("AA001");
        //Assert
        assertNull(result);
    }

    @Test
    void ensureThatPercentageOfAllocationIsReturned() {
        //Arrange
        float expected = 45.0f;
        //Act
        float result = accountInProject.getPercentageOfAllocation();
        //Assert
        assertEquals(expected,result);

    }

    @Test
    void ensureThatAllProjectsInAccountsAreListedSuccessfully() {
        //Arrange
        Project expected = project;
        //Act
        Project result = accountInProject.getProjectByAccount("john@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatGetProjectByAccountReturnsNull() {
        //Act
        Project result = accountInProject.getProjectByAccount("emma@isep.ipp.pt");
        //Assert
        assertNull(result);
    }

    /**
     * Testing if percentage of allocation is valid when equals 100%.
     */
    @Test
    void ensurePercentAllocationIsValidIfEqualsOneHundred() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = accountInProject.isPercentageOfAllocationValid(100);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if percentage of allocation is invalid when above 100%.
     */
    @Test
    void ensurePercentAllocationIsInvalidIfAboveOneHundred() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProject.isPercentageOfAllocationValid(151);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if percentage of allocation is invalid when below 0%.
     */
    @Test
    void ensurePercentAllocationIsInvalidIfBelowZero() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProject.isPercentageOfAllocationValid(-20);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if percentage of allocation is invalid when allocating an account at 0%.
     */
    @Test
    void ensurePercentAllocationIsInvalid_NewAllocationInvalid() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = accountInProject.isPercentageOfAllocationValid(0);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsTrueIfStartDateIsBeforeNow() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = accountInProject.isStartDateBeforeNow();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsFalseIfStartDateIsAfterNow() {
        // Arrange
        boolean expected = false;
        startDate = LocalDate.of(2023, 12, 19);
        endDate = LocalDate.of(2024, 12,19);

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isStartDateBeforeNow();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfStartDateIsNow() {
        // Arrange
        boolean expected = true;
        startDate = LocalDate.now();
        endDate = LocalDate.of(2024, 12,19);

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isStartDateBeforeNow();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfEndDateIsAfterNow() {
        // Arrange
        boolean expected = true;
        startDate = LocalDate.now();
        endDate = LocalDate.of(2024, 12,19);

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isEndDateAfterNow();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsFalseIfEndDateIsBeforeNow() {
        // Arrange
        boolean expected = false;
        startDate = LocalDate.of(2022, 2,21);
        endDate = LocalDate.of(2023, 2,21);

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isEndDateAfterNow();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfEndDateIsNow() {
        // Arrange
        boolean expected = true;
        startDate = LocalDate.of(2022, 2,21);
        endDate = LocalDate.now();

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isEndDateAfterNow();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfEndDateIsAfterStartDate() {
        // Arrange
        boolean expected = true;
        startDate = LocalDate.of(2022, 2,21);
        endDate = LocalDate.now();

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isEndDateValid();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsFalseIfEndDateIsBeforeStartDate() {
        // Arrange
        boolean expected = false;
        startDate = LocalDate.of(2025, 2,23);
        endDate = LocalDate.now();

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isEndDateValid();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsFalseIfEndDateIsEqualToStartDate() {
        // Arrange
        boolean expected = false;
        startDate = LocalDate.now();
        endDate = LocalDate.now();

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Act
        boolean result = accountInProject.isEndDateValid();

        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfRoleIsEmpty() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProjectIncomplete.isRoleEmpty();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsFalseIfRoleIsNotEmpty() {
        //Arrange
        boolean expected = false;

        //Act
        boolean result = accountInProject.isRoleEmpty();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureRoleIsRetrieved() {
        //Arrange
        String expected = "team member";

        //Act
        String result = accountInProject.getRole();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureEmptyRoleIsRetrieved() {
        //Arrange
        String expected = "";

        //Act
        String result = accountInProjectIncomplete.getRole();

        //Assert
        assertEquals(expected,result);
    }


    @Test
    void ensureThatReturnsTrueIfAccountInProjectHasSpecifiedProject() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.hasProject(project);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsFalseIfAccountInProjectDoesNotHaveSpecifiedProject() {
        //Arrange
        otherProject = new Project("2A", "other project code", new Customer("Poppy",
                "228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("IT Sector") );
        boolean expected = false;

        //Act
        boolean result = accountInProject.hasProject(otherProject);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfAccountInProjectHasSpecifiedAccount() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.hasAccount(account);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsFalseIfAccountInProjectDoesNotHaveSpecifiedAccount() {
        //Arrange
        otherAccount = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        boolean expected = false;

        //Act
        boolean result = accountInProject.hasAccount(otherAccount);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatReturnsTrueIfRoleIsTeamMember() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.isTeamMember();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsFalseIfRoleIsProductOwner() {
        //Arrange
        accountInProjectProductOwner = new AccountInProject(account, project, "Product " +
                "Owner",
                costPerHour, percentageAllocation, startDate, endDate);
        boolean expected = false;

        //Act
        boolean result = accountInProjectProductOwner.isTeamMember();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsFalseIfRoleIsScrumMaster() {
        //Arrange
        accountInProjectScrumMaster = new AccountInProject(account, project, "Scrum " +
                "Master",
                costPerHour, percentageAllocation, startDate, endDate);
        boolean expected = false;

        //Act
        boolean result = accountInProjectScrumMaster.isTeamMember();

        //Assert
        assertEquals(expected, result);
    }


    /**
     * This test verifies if a copy of the attribute project of an instance of
     * AccountInProject is retrieved. It should return a new instance of Project
     * with the same information of the attribute project.
     */
    @Test
    void ensureThatProjectIsRetrievedIfTheProjectAttributeOfAccountInProjectIsNotNull() {
        //Arrange
        Project expected = project;
        //Act
        Project result = accountInProject.getProject();
        //Assert
        assertEquals(expected, result);
    }

}
