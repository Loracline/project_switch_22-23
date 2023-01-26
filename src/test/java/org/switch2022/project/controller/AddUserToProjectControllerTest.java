package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * This class tests the implementation of these three US:
 * US011 - As Manager, I want to associate a user as Team Member of a project.
 * US012 - As Manager, I want to define the PO of a project.
 * US013 - As Manager, I want to define the SM of a project.
 */

class AddUserToProjectControllerTest {

/**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */


    //accounts
    Account accountOne, accountTwo, accountThree;
    List<Account> accounts;
    AccountContainer accountContainer;

    //profiles
    Profile profileOne, profileTwo, profileThree;
    List<Profile> profiles;
    ProfileContainer profileContainer;

    //customers
    Customer customerOne, customerTwo;
    List<Customer> customers;
    CustomerContainer customerContainer;

    //projects
    Project projectOne, projectTwo;
    List<Project> projects;
    ProjectContainer projectContainer;

    //project typologies
    ProjectTypology projectTypologyOne, projectTypologyTwo;
    List<ProjectTypology> typologies;
    ProjectTypologyContainer projectTypologyContainer;

    //business sectors
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;

    //accounts in project
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2, accountInProject3;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;

    //company
    Company company;

    @BeforeEach
    void setUp() {
        //accounts
        accountOne = new Account("Mike", "mike@isep.ipp.pt",
                932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt",
                932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt",
                932755687, null);

        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        //profiles
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");

        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        //business sectors
        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        //project typologies
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        projectTypologyTwo = new ProjectTypology(
                "Fixed time and materials");

        typologies = new ArrayList<>();
        projectTypologyContainer = new ProjectTypologyContainer(typologies);
        typologies.add(projectTypologyOne);
        typologies.add(projectTypologyTwo);

        //customers
        customerOne = new Customer("ISEP", "222333444");
        customerTwo = new Customer("PortoTech", "222333445");

        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);
        customers.add(customerOne);
        customers.add(customerTwo);

        //projects
        projectOne = new Project("proj001", "software development", new Customer ("John", "228674498"),new ProjectTypology("Fixed cost")
                , new BusinessSector( "Hunting"));
        projectTwo = new Project("proj002", "project software", new Customer ("John", "228674498"),
                new ProjectTypology("Fixed cost")
                , new BusinessSector( "Hunting"));

        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectContainer = new ProjectContainer(projects);

        //accounts in project
        accountInProject1 = new AccountInProject(accountOne, projectOne, "Team Member",
                7.5f, 40.0f, startDate);
        accountInProject2 = new AccountInProject(accountTwo, projectTwo, "Product Owner",
                7.5f, 40.0f, startDate);
        accountInProject3 = new AccountInProject(accountThree, projectOne, "Scrum Master",
                7.5f, 40.0f, startDate);

        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);
        accountsInProject.add(accountInProject3);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        //company
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer,
                customerContainer);
    }

    @AfterEach
    void tearDown() {
        //accounts
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        accounts.clear();
        accountContainer = null;

        //profiles
        profileOne = null;
        profileTwo = null;
        profileThree = null;
        profiles.clear();
        profileContainer = null;

        //business sectors
        businessSector = null;
        businessSectors.clear();
        businessSectorContainer = null;

        //project typologies
        projectTypologyOne = null;
        projectTypologyTwo = null;
        typologies.clear();
        projectTypologyContainer = null;

        //customers
        customerOne = null;
        customerTwo = null;
        customers.clear();
        customerContainer = null;

        //projects
        projectOne = null;
        projectTwo = null;
        projects.clear();
        projectContainer = null;

        //accounts in project
        accountInProject1 = null;
        accountInProject2 = null;
        accountInProject3 = null;
        accountsInProject.clear();
        accountInProjectContainer = null;

        //company
        company = null;
    }

/**
     * US012 - As Manager, I want to define the PO of a project.
     */

    @Test
    void ensureAccountIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManager = "jane@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO("Emma", "emma@isep.ipp.pt", true);

        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("proj001", "software development", "John",
                "Fixed cost", "Hunting");

        //account in project dto - product owner
        AllocationDTO allocationDTOPO = new AllocationDTO("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), null);

        AddUserToProjectController controller = new AddUserToProjectController(company);

        //Act
        boolean result = controller.addUserToProject(emailManager, accountDTO, projectDTO,
                allocationDTOPO);

        //Assert
        assertTrue(result);

    }

    @Test
    void ensureAllocationFailsBecauseProfileIsNotManager() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManagerInvalid = "kate@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO("Emma", "emma@isep.ipp.pt", true);

        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("proj001", "software development", "John",
                "Fixed cost", "Hunting");

        //account in project dto - product owner
        AllocationDTO allocationDTOPO = new AllocationDTO("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), null);

        AddUserToProjectController controller = new AddUserToProjectController(company);
        //Act
        boolean result = controller.addUserToProject(emailManagerInvalid, accountDTO,
                projectDTO,
                allocationDTOPO);

        //Assert
        assertFalse(result);
    }
}
