package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        /*
          Account in project created.
         */
        account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        project = new Project("1A", "project code", new Customer("John"),
                new ProjectTypology("Fixed cost"),new BusinessSector("IT Sector") );
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
     * Testing the equals() method.
     */
    @Test
    void ensureSameAccountInProjectEqualsItself() {
        // Arrange
        AccountInProject clone = accountInProject;
        boolean expected = true;
        // Act
        boolean result = accountInProject.equals(clone);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountsInProjectAreEqual() {
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
    void ensureTwoAccountsInProjectAreDifferent() {
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
    void ensureAccountInProjectDoesNotEqualsNull() {
        // Assert
        assertNotNull(accountInProject);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureAccountInProjectDoesNotEqualOtherTypeOfObject() {
        // Arrange
        boolean expected = false;
        // Act
        boolean result = accountInProject.equals(account);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * hashCode()
     */
    @Test
    void ensureAccountsInProjectHaveSameHashCode() {
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
    void ensureAccountsInProjectHaveDifferentHashCode() {
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
     * Testing if one is able to validate the role of the account in project.
     */
    @Test
    void ensureProductOwnerIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Product Owner", costPerHour, percentageAllocation, startDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureTeamMemberIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Team Member", costPerHour, percentageAllocation, startDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureScrumMasterIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Scrum Master", costPerHour, percentageAllocation, startDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureProjectManagerIsAValidRole() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Project Manager", costPerHour, percentageAllocation, startDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureAnyOtherRoleIsInvalid() {
        //Arrange
        AccountInProject accountInProject = new AccountInProject(account, project,
                "Product Visionary", costPerHour, percentageAllocation, startDate);
        //Act
        boolean result = accountInProject.isRoleValid();
        //Assert
        assertFalse(result);
    }

    @Test
    void ensureAccountIsRetrievedSuccessfully() {
        //Arrange
        Account expected = account;
        //Act
        Account result = accountInProject.getAccountByProject("1A");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatGetAccountByProjectReturnsNull() {
        //Act
        Account result = accountInProject.getAccountByProject("AA001");
        //Assert
        assertNull(result);
    }

    @Test
    void ensureThatAllProjectsInAccountsAreListedSuccessfully() {
        List<Project> expected = new ArrayList<>();
        expected.add(projectOne);
        expected.add(projectTwo);


        //Act
        List<Project> result = accountInProjectContainerOne.listProjectsByAccount("mike@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListProjectsInAccountIsEmpty() {
        List<Project> expected = new ArrayList<>();

        //Act
        List<Project> result = accountInProjectContainerOne.listProjectsByAccount("mary@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }
}
