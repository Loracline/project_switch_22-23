package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AccountInProjectTest {
    Account account;
    Project project;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject;

    @BeforeEach
    void setUp() {

        //Account in project created.
        account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        project = new Project("1A", "project code", new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"), new BusinessSector("IT Sector"));
        costPerHour = 7.5f;
        percentageAllocation = 45.0f;
        startDate = LocalDate.of(2023, 1, 19);

        accountInProject = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate);
    }

    @AfterEach
    void tearDown() {
        account = null;
        project = null;
        accountInProject = null;
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
                costPerHour, percentageAllocation, startDate);
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
                costPerHour, percentageAllocation, startDate);
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
                costPerHour, percentageAllocation, startDate);
        AccountInProject other = new AccountInProject(account, project, "Team Member",
                costPerHour, percentageAllocation, startDate);

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
                costPerHour, percentageAllocation, startDate);
        AccountInProject other = new AccountInProject(account, project, "Team Member",
                7.0f, percentageAllocation, startDate);

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
                "Product Owner", costPerHour, percentageAllocation, startDate);
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
                "Team Member", costPerHour, percentageAllocation, startDate);
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
                "Scrum Master", costPerHour, percentageAllocation, startDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    /**
     * Test to validate if Project Manager as a valid role to an account in project. It should return true.
     */

    @Test
    void ensureThatProjectManagerIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Project Manager", costPerHour, percentageAllocation, startDate);
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
                "Product Visionary", costPerHour, percentageAllocation, startDate);
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
        LocalDate expected = LocalDate.of(2023, 12, 25);
        //Act
        accountInProject.setEndDate(LocalDate.of(2023, 12, 25));
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
     * Test to verify if an account is retrieved by giving a invalid project code. It should return null.
     */

    @Test
    void ensureThatGetAccountByProjectReturnsNullWithInvalidProjectCode() {
        //Act
        Account result = accountInProject.getAccountByProject("AA001");
        //Assert
        assertNull(result);
    }
}
