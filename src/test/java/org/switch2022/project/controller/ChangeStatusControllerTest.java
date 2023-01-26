package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the implementation of the following US:
 * US005 - As Administrator, I want to inactivate a user account.
 * US006 - As Administrator, I want to activate a user account.
 */
class ChangeStatusControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    // accounts
    Account accountOne, accountTwo, accountThree, accountFour;
    AccountContainer accountContainer;

    // profiles
    Profile profileOne, profileTwo;
    ProfileContainer profileContainer;

    // business sectors
    BusinessSectorContainer businessSectorContainer;

    // customers
    CustomerContainer customerContainer;
    List<Customer> customers;

    // project typologies
    ProjectTypologyContainer projectTypologyContainer;

    // projects
    Project project;
    ProjectContainer projectContainer;

    // accounts in project
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;

    // company
    Company company;

    // controller
    ChangeStatusController changeStatusController;

    @BeforeEach
    void setUp() {
        // accounts
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountThree = new Account("Paul", "paul@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Paul", "paul@isep.ipp.pt", 932755689, null);

        List<Account> accountList = new ArrayList<>();
        accountContainer = new AccountContainer(accountList);
        accountList.add(accountOne);
        accountList.add(accountTwo);
        accountList.add(accountThree);
        accountList.add(accountFour);

        // profiles
        profileOne = new Profile("Administrator");

        List<Profile> profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);

        // projects
        project = new Project("proj001", "software development management",
                new Customer("John","228674498"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));

        List<Project> projects = new ArrayList<>();
        projectContainer = new ProjectContainer(projects);
        projects.add(project);

        // project typologies
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        customers = new ArrayList<>();
        customerContainer = new CustomerContainer();

        // accounts in project
        accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
                costPerHour, percentageAllocation, startDate);
        accountInProject2 = new AccountInProject(accountTwo, project, "Team Member",
                costPerHour, percentageAllocation, startDate);

        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        // company
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        // controller
        changeStatusController = new ChangeStatusController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        project = null;
        projectContainer = null;
        company = null;
        changeStatusController = null;
        customers.clear();
        customerContainer = null;
        company = null;
        projectTypologyContainer = null;
    }

    /**
     * changeStatus(String email, boolean status)
     */
    @Test
    void ensureAccountStatusIsChangedToInactive() {
        // ARRANGE
        boolean expected = true;

        // ACT
        boolean result = changeStatusController.changeStatus(accountOne.getEmail(), false);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountStatusIsNotChangedStillActive() {
        // ARRANGE
        boolean expected = false;

        // ACT
        boolean result = changeStatusController.changeStatus(accountOne.getEmail(), true);

        // ASSERT
        assertEquals(expected, result);
    }
}