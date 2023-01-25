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

class ListAccountsInProjectControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour;
    AccountDTO accountDTOOne, accountDTOTwo, accountDTOThree;
    Profile profileOne, profileTwo, profileThree;
    Customer customerOne, customerTwo;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne, projectTwo;
    AccountInProject accountInProjectOne, accountInProjectTwo, accountInProjectThree, accountInProjectFour, accountInProjectFive;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    List<Account> accounts;
    List<AccountDTO> accountsDTOOne;
    List<Profile> profiles;
    List<ProjectTypology> typologies;
    List<BusinessSector> businessSectors;
    List<Project> projects;
    List<AccountInProject> accountsInProject;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSectorContainer businessSectorContainer;
    ProjectContainer projectContainer;
    ProjectTypologyContainer projectTypologyContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    ListAccountsInProjectController listAccountsInProjectController;
    CustomerContainer customerContainer;
    List<Customer> customers;

    @BeforeEach
    void setUp() {

        costPerHour = 1;
        percentageAllocation = 1;
        //startDate = LocalDate.now();
        startDate = LocalDate.of(2020, 1, 8);

        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountThree = new Account("Anna", "anna@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);
        accounts.add(accountFour);

        //accountDTO
        accountDTOOne = AccountMapper.accountToDTO(accountOne);
        accountDTOTwo = AccountMapper.accountToDTO(accountTwo);
        accountDTOThree = AccountMapper.accountToDTO(accountThree);
        accountsDTOOne = new ArrayList<>();
        accountsDTOOne.add(accountDTOOne);
        accountsDTOOne.add(accountDTOTwo);
        accountsDTOOne.add(accountDTOThree);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");
        profiles = new ArrayList<>();
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        //customer
        customerOne = new Customer("Genius Software", "234567890");
        customerTwo = new Customer("Delta Software", "245567789");
        customers = new ArrayList<>();
        customers.add(customerOne);
        customers.add(customerTwo);

        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        typologies = new ArrayList<>();
        typologies.add(projectTypologyOne);

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");
        businessSectors = new ArrayList<>();
        businessSectors.add(businessSectorOne);

        //project
        projectOne = new Project("1A", "Mobile Software", new Customer("Genious " +
                "Software","228674498"), new ProjectTypology("Fixed Cost"), new BusinessSector("Fishing") );
        projectTwo = new Project("2B", "Software Development Management", new Customer(
                "Delta Software","228674498"), new ProjectTypology("Fixed Cost"),new BusinessSector("Fishing"));
        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);

        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", costPerHour, percentageAllocation, startDate);
        accountInProjectTwo = new AccountInProject(accountTwo, projectOne, "Team Member", costPerHour, percentageAllocation, startDate);
        accountInProjectThree = new AccountInProject(accountThree, projectOne, "Product Owner", costPerHour, percentageAllocation, startDate);
        accountInProjectFour = new AccountInProject(accountThree, projectTwo, "Scrum Master", costPerHour, percentageAllocation, startDate);
        accountInProjectFive = new AccountInProject(accountOne, projectTwo, "Team Member", costPerHour, percentageAllocation, startDate);
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);
        accountsInProject.add(accountInProjectFour);
        accountsInProject.add(accountInProjectFive);

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
        listAccountsInProjectController = new ListAccountsInProjectController(company);

        accountFour.setProfile(profileThree);
    }

    @AfterEach
    void tearDown() {

        accountOne = null;
        accountTwo = null;
        accountThree = null;
        accountFour = null;
        accountDTOOne = null;
        accountDTOTwo = null;
        accountDTOThree = null;
        profileOne = null;
        profileTwo = null;
        profileThree = null;
        customerOne = null;
        customerTwo = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        projectOne = null;
        projectTwo = null;
        accountInProjectOne = null;
        accountInProjectTwo = null;
        accountInProjectThree = null;
        accountInProjectFour = null;
        accountInProjectFive = null;
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
        listAccountsInProjectController = null;
        customerContainer = null;
    }

    @Test
    void ensureAllAccountsByProjectAreListedSuccessfully() {
        //Arrange
        List<AccountDTO> expected = accountsDTOOne;

        //Act
        List<AccountDTO> result = listAccountsInProjectController.listAccountsByProject("mary@isep.ipp.pt", "1A");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListAccountsByProjectIsEmpty_NoPermission() {
        //Arrange
        List<AccountDTO> expected = new ArrayList<>();
        accountFour.setProfile(profileOne);

        //Act
        List<AccountDTO> result = listAccountsInProjectController.listAccountsByProject("mary@isep.ipp.pt", "1A");

        //Assert
        assertEquals(expected, result);
    }
}