package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountInProjectTest {
    Account account;
    Customer customer;
    ProjectTypology projectTypology;
    BusinessSector businessSector;
    Project project;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject;

    @BeforeEach
    void setUp() {
        account = new Account("John", "john@isep.ipp.pt", 912345678, null);
        customer = new Customer("IT Customer");
        projectTypology = new ProjectTypology("fixed cost");
        businessSector = new BusinessSector("IT Sector");
        project = new Project("1A", "project One", customer, projectTypology,
                businessSector);
        costPerHour = 7.5f;
        percentageAllocation = 45.0f;
        startDate = LocalDate.of(2023,01,19);
        accountInProject = new AccountInProject(account, project,
                costPerHour, percentageAllocation, startDate);
        accountInProject.setRole("team member");
    }

    @AfterEach
    void tearDown() {
        account = null;
        customer = null;
        projectTypology = null;
        businessSector = null;
        project = null;
        accountInProject = null;
    }


    @Test
    void ensureThatTeamMemberIsAdded() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.setRole("team member");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatProductOwnerIsAdded() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.setRole("product owner");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatProjectManagerIsAdded() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.setRole("project manager");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatScrumMasterIsAdded() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountInProject.setRole("scrum master");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatInvalidRoleIsNotAdded() {
        //Arrange
        boolean expected = false;

        //Act
        boolean result = accountInProject.setRole("human resources manager");

        //Assert
        assertEquals(expected, result);
    }

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
        AccountInProject copy = new AccountInProject(account, project,
                costPerHour, percentageAllocation, startDate);
        copy.setRole("team member");
        boolean expected = true;
        // Act
        boolean result = accountInProject.equals(copy);
        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureTwoAccountsInProjectAreDifferent() {
        // Arrange
        accountInProject.setRole("team member");
        AccountInProject other = new AccountInProject(account, project,
                costPerHour, percentageAllocation, startDate);
        other.setRole("product owner");
        boolean expected = false;
        // Act
        boolean result = accountInProject.equals(other);
        // Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureAccountInProjectDoesNotEqualsNull() {
        // Assert
        assertNotNull(accountInProject);
    }

    @Test
    void ensureAccountInProjectDoesNotEqualOtherTypeOfObject() {
        // Arrange
        boolean expected = false;
        // Act
        boolean result = accountInProject.equals(account);

        // Assert
        assertEquals(expected,result);
    }
}
