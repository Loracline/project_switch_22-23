package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AccountInProjectDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AddUserToProjectControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
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
    //account in project
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    //company
    Company company;


    @BeforeEach
    void setUp() {
        //accounts
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
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

        //business sectors
        businessSector = new BusinessSector("fishing");
        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);


        //project typologies
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        projectTypologyTwo = new ProjectTypology("Fixed time and materials");
        typologies = new ArrayList<>();
        projectTypologyContainer = new ProjectTypologyContainer(typologies);
        typologies.add(projectTypologyOne);
        typologies.add(projectTypologyTwo);

        //customers
        customerOne = new Customer("ISEP");
        customerTwo = new Customer("PortoTech");

        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);
        customers.add(customerOne);
        customers.add(customerTwo);

        //projects
        projectOne = new Project("proj001", "software development management",
                customerOne,
                projectTypologyOne,
                businessSector);
        projectTwo = new Project("proj002", "project software", customerTwo,
                projectTypologyTwo,
                businessSector);
        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectContainer = new ProjectContainer(projects);

        //accounts in project
        accountInProject1 = new AccountInProject(accountOne, projectOne, "Team Member",
                7.5f, 40.0f, startDate);
        accountInProject2 = new AccountInProject(accountTwo, projectTwo, "Product Owner",
                7.5f, 40.0f, startDate);

        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);
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
        accountsInProject.clear();
        accountInProjectContainer = null;
        //company
        company = null;
    }

    @Test
    void ensureProductOwnerIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";
        accountDTO.phoneNumber = 912345678;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001","Test",customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - product owner
        AccountInProjectDTO accountInProjectDTOPO = new AccountInProjectDTO();
        accountInProjectDTOPO.accountDTO = accountDTO;
        accountInProjectDTOPO.projectDTO = projectDTO;
        accountInProjectDTOPO.role = "Product Owner";
        accountInProjectDTOPO.costPerHour = 7.5f;
        accountInProjectDTOPO.percentageAllocation = 45.0f;
        accountInProjectDTOPO.startDate = LocalDate.of(2023, 01, 19);
        accountInProjectDTOPO.endDate = LocalDate.of(2023, 01, 22);

        //Act
        boolean result = company.addUserToProject(accountInProjectDTOPO);

        //Assert
        assertTrue(result);

    }

    @Test
    void ensureTeamMemberIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "Anna";
        accountDTO.email = "anna@isep.ipp.pt";
        accountDTO.phoneNumber = 912345688;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001","Test",customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - product owner
        AccountInProjectDTO accountInProjectDTOTM = new AccountInProjectDTO();
        accountInProjectDTOTM.accountDTO = accountDTO;
        accountInProjectDTOTM.projectDTO = projectDTO;
        accountInProjectDTOTM.role = "Team Member";
        accountInProjectDTOTM.costPerHour = 7.5f;
        accountInProjectDTOTM.percentageAllocation = 45.0f;
        accountInProjectDTOTM.startDate = LocalDate.of(2023, 01, 19);
        accountInProjectDTOTM.endDate = LocalDate.of(2023, 01, 22);

        //Act
        boolean result = company.addUserToProject(accountInProjectDTOTM);

        //Assert
        assertTrue(result);

    }

}