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

class RegisterAccountControllerTest {

    /**
     * BeforeEach and
     * AfterEach executes
     * common code
     * before/
     * after running
     * the tests
     * below .
     */

    Account accountOne, accountTwo, accountThree;
    Profile profileOne, profileTwo, profileThree;
    Project project;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSectorContainer businessSectorContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    CustomerContainer customerContainer;

    Company company;

    RegisterAccountController controller;

    @BeforeEach
    void setUp() {
        //create users
        accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);

        //initialize account container with filled account list
        accountContainer = new AccountContainer();

        //add users to accounts list
        accountContainer.addAccount("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountContainer.addAccount("Jane", "jane@isep.ipp.pt", 932755687, null);


        //create profiles
        profileOne = new Profile("Manager");
        profileTwo = new Profile("Administrator");
        profileThree = new Profile("User");

        //initialize profile container with filled profiles list
        profileContainer = new ProfileContainer();

        //add profiles to profiles list
        profileContainer.createProfile("Manager");
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("User");

        projectTypologyContainer = new ProjectTypologyContainer();

        customerContainer = new CustomerContainer();

        project = new Project("proj001", "software development management", new Customer(
                "ISEP", "228674498"),
                new ProjectTypology("Fixed cost"), new BusinessSector("fishing"));

        projectContainer = new ProjectContainer();

        accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
                costPerHour, percentageAllocation, startDate);
        accountInProject2 = new AccountInProject(accountTwo, project, "Team Member",
                costPerHour, percentageAllocation, startDate);
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
        //initialize company with filled containers

        //initialize controller with company
        controller = new RegisterAccountController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        profileOne = null;
        profileTwo = null;
        profileThree = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        project = null;
        projectContainer = null;
        company = null;
        customerContainer = null;
        controller = null;
    }

    @Test
    void ensureThatNewAccountIsRegisteredIfEmailIsUnique() {
        //ARRANGE
        boolean expected = true;

        //ACT
        boolean result = controller.registerAccount("Charlotte", "charlotte@isep.ipp.pt", 932755677, null);

        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void ensureThatNewAccountIsNotRegisteredIfEmailIsDuplicated() {
        //ARRANGE
        boolean expected = false;

        //ACT
        boolean result = controller.registerAccount("Emma", "emma@isep.ipp.pt", 932755688, null);

        //ASSERT
        assertEquals(expected, result);
    }
}