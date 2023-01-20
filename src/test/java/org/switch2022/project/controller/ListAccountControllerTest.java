package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BeforeEach and AfterEach executes common code before/after running the tests below.
 */

class ListAccountControllerTest {
    Account accountOne, accountTwo;
    Profile profileOne, profileTwo;
    ProjectTypology projectTypology;
    Project project;
    Customer customer;
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    Company company;

    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);


        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");


        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);

        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer= new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        projectTypology = new ProjectTypology("Fixed Cost");

        List<ProjectTypology> typologies = new ArrayList<>();
        typologies.add(projectTypology);
        projectTypologyContainer = new ProjectTypologyContainer(typologies);

        customer = new Customer("ISEP");
        project = new Project("proj001", "software development management", customer,
                projectTypology, businessSector);

        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projectContainer = new ProjectContainer(projects);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        profileTwo = null;
        accounts.clear();
        profiles.clear();
        accountContainer = null;
        profileContainer = null;
        businessSector=null;
        businessSectors.clear();
        businessSectorContainer=null;
        company = null;
    }

    @Test
    void ensureAllAccountsAreListedSuccessfully() {
        List<Account> expected = accountContainer.getAccounts();
        ListAccountController newListAccountController = new ListAccountController(company);

        // Act
        List<Account> result = newListAccountController.listAllAccounts();

        // Assert
        assertEquals(expected, result);
    }

}