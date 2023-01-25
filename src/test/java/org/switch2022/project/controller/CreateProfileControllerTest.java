package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class CreateProfileControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour;
    Profile profileOne, profileTwo;
    Project project;
    List<Account> accounts;
    List<Profile> profiles;
    List<Project> projects;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    CustomerContainer customerContainer;
    List<Customer> customers;

    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    Company company;

    CreateProfileController createProfileController;


    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountThree = new Account("Anna", "anna@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);

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

        projects = new ArrayList<>();
        projectContainer = new ProjectContainer(projects);
        projects.add(project);

        businessSectors = new ArrayList<>();
        businessSectorContainer= new BusinessSectorContainer(businessSectors);

        List<ProjectTypology> typologies = new ArrayList<>();
        projectTypologyContainer = new ProjectTypologyContainer(typologies);

        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);

        project = new Project("proj001", "software development management", "ISEP",
                "Fixed cost", "fishing");

        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projectContainer = new ProjectContainer(projects);

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

        createProfileController = new CreateProfileController(company);
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
        businessSectors.clear();
        businessSectorContainer=null;
        project = null;
        projects.clear();
        projectContainer = null;
        customers.clear();
        customerContainer = null;
        company = null;
        createProfileController = null;
    }
    @Test
    void addNewProfileSuccessfully(){
        //Arrange
        boolean expected = true;
        //Act
        boolean result = createProfileController.createProfile("Manager");
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addNewProfileUnsuccessfullyInvalidName(){
        //Arrange
        boolean expected = false;
        //Act
        boolean result = createProfileController.createProfile("User");
        //Assert
        assertEquals(expected,result);
    }
}