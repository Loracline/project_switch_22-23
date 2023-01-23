package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddCustomerControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour;
    Profile profileOne, profileTwo;
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    Project project;
    List<Project> projects;
    ProjectContainer projectContainer;
    Company company;
    ProjectTypology projectTypology;
    ProjectTypologyContainer projectTypologyContainer;
    Customer customerOne, customerTwo;
    CustomerContainer customerContainer;
    List<Customer> customers;
    AddBusinessSectorController addBusinessSectorController;
    AddCustomerController addCustomerController;

    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;


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

        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        projectTypology = new ProjectTypology("Fixed Cost");

        List<ProjectTypology> typologies = new ArrayList<>();
        typologies.add(projectTypology);
        projectTypologyContainer = new ProjectTypologyContainer(typologies);

        customerOne = new Customer("ISEP");
        customerTwo = new Customer("PortoTech");

        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);
        customers.add(customerOne);
        customers.add(customerTwo);

        project = new Project("proj001", "software development management", customerOne,
                projectTypology, businessSector);

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
                projectContainer, projectTypologyContainer, accountInProjectContainer,customerContainer);

        addBusinessSectorController = new AddBusinessSectorController(company);
        addCustomerController = new AddCustomerController(company);
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
        businessSector = null;
        businessSectors.clear();
        businessSectorContainer = null;
        project = null;
        projects.clear();
        projectContainer = null;
        company = null;
        addBusinessSectorController = null;
        addCustomerController = null;
    }
    @Test
    void addNewCustomerSuccessfully(){
        //Arrange
        boolean expected = true;
        accountOne.setProfile(profileOne);
        //Act
        boolean result = addCustomerController.addCustomer("Critical", "mike@isep.ipp.pt");
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addNewCustomerUnsuccessfullyInvalidName(){
        //Arrange
        boolean expected = false;
        accountOne.setProfile(profileOne);
        //Act
        boolean result = addCustomerController.addCustomer("ISEP", "mike@isep.ipp.pt");
        //Assert
        assertEquals(expected,result);
    }



    @Test
    void addNewCustomerUnsuccessfullyEmptyName() {
        // Arrange
        boolean expected = false;
        accountOne.setProfile(profileOne);
        // Act
        boolean result = addCustomerController.addCustomer("", "mike@isep.ipp.pt");
        // Assert
        assertEquals(expected, result);
    }


}