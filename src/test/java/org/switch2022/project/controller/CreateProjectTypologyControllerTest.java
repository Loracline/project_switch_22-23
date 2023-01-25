package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.mapper.AccountMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CreateProjectTypologyControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo;
    AccountDTO accountDTOOne;
    Profile profileOne, profileTwo;
    Customer customerOne;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    AccountInProject accountInProjectOne;
    List<Account> accounts;
    List<AccountDTO> accountsDTOOne;
    List<Profile> profiles;
    List<ProjectTypology> typologies;
    List<BusinessSector> businessSectors;
    List<Project> projects;
    List<Customer> customers;
    List<AccountInProject> accountsInProject;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSectorContainer businessSectorContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    ProjectTypologyContainer projectTypologyContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    CreateProjectTypologyController createProjectTypologyController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);

        //accountDTO
        accountDTOOne = AccountMapper.accountToDTO(accountOne);
        accountsDTOOne = new ArrayList<>();
        accountsDTOOne.add(accountDTOOne);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");
        profiles = new ArrayList<>();
        profiles.add(profileOne);
        profiles.add(profileTwo);

        //customer
        customerOne = new Customer("Genius Software", "234567890");
        customers = new ArrayList<>();
        customers.add(customerOne);

        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        typologies = new ArrayList<>();
        typologies.add(projectTypologyOne);

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");
        businessSectors = new ArrayList<>();
        businessSectors.add(businessSectorOne);

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne );
        projects = new ArrayList<>();
        projects.add(projectOne);

        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", 1,
                34f, LocalDate.of(2020, 1, 8));
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);

        //containers
        accountContainer = new AccountContainer(accounts);
        profileContainer = new ProfileContainer(profiles);
        businessSectorContainer = new BusinessSectorContainer(businessSectors);
        projectContainer = new ProjectContainer(projects);
        projectTypologyContainer = new ProjectTypologyContainer(typologies);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        customerContainer = new CustomerContainer(customers);

        //company
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        //controller
        createProjectTypologyController = new CreateProjectTypologyController(company);
        accountTwo.setProfile(profileTwo);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountDTOOne = null;
        profileOne = null;
        profileTwo = null;
        customerOne = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        projectOne = null;
        accountInProjectOne = null;
        accounts.clear();
        accountsDTOOne.clear();
        profiles.clear();
        typologies.clear();
        businessSectors.clear();
        projects.clear();
        accountsInProject.clear();
        customers.clear();
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectContainer = null;
        projectTypologyContainer = null;
        accountInProjectContainer = null;
        company = null;
        createProjectTypologyController = null;
        customerContainer = null;
    }

    /**
     * Test to verify if a project typology is successfully created.
     */

    @Test
    void ensureThatNewProjectTypologyIsSuccessfullyAdded(){
        //Arrange
        accountOne.setProfile(profileOne);
        boolean expected = true;
        //Act
        boolean result = createProjectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed new typology");
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test if the desired typology already exists. If it does, new typology is not created.
     */

    @Test
    void ensureThatNewProjectTypologyIsNotCreatedIfItAlreadyExists(){
        //Arrange
        accountOne.setProfile(profileOne);
        boolean expected = false;
        //Act
        boolean result = createProjectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed Cost");
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that project typology is not added if account doesn't have authorization.
     */

    @Test
    void ensureThatNewProjectTypologyIsNotCreatedIfItAccountIsNotAuthorized(){
        //Arrange
        boolean expected = false;
        //Act
        boolean result = createProjectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed new typology");
        //Assert
        assertEquals(expected,result);
    }

}