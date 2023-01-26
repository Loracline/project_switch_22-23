package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.utils.mapper.AccountMapper;
import org.switch2022.project.utils.mapper.ProjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListProjectsInAccountControllerTest {
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
    List<Project> projects;
    List<AccountInProject> accountsInProject;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSectorContainer businessSectorContainer;
    ProjectContainer projectContainer;
    ProjectTypologyContainer projectTypologyContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    CustomerContainer customerContainer;
    List<Customer> customers;

    ProjectDTO projectDTOOne, projectDTOTwo;

    List<ProjectDTO> projectsDTOOne;
    ListProjectsInAccountController listProjectsInAccountController;

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

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne );
        projectTwo = new Project("2B", "Software Development Management",customerOne, projectTypologyOne,businessSectorOne);
        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);

        //projectDTO
        projectDTOOne = ProjectMapper.getDTOFromProject(projectOne);
        projectDTOTwo = ProjectMapper.getDTOFromProject(projectTwo);
        projectsDTOOne = new ArrayList<>();
        projectsDTOOne.add(projectDTOOne);
        projectsDTOOne.add(projectDTOTwo);

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
        businessSectorContainer = new BusinessSectorContainer();
        projectContainer = new ProjectContainer(projects);
        projectTypologyContainer = new ProjectTypologyContainer(typologies);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        customerContainer = new CustomerContainer(customers);

        //company
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        //controller
        listProjectsInAccountController = new ListProjectsInAccountController(company);

        accountFour.setProfile(profileThree);//perguntar se é melhor usar um setter para os testes ou um construtor
        // (para atribuir uma company a um controller)
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
        projects.clear();
        accountsInProject.clear();
        customers.clear();
        projectsDTOOne.clear();
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectContainer = null;
        projectTypologyContainer = null;
        accountInProjectContainer = null;
        company = null;
        listProjectsInAccountController = null;
        customerContainer = null;
        projectDTOOne = null;
        projectDTOTwo = null;
        projectsDTOOne = null;
    }

    @Test
    void ensureAllProjectsByAccountAreListedSuccessfully() {
        //Arrange
        List<ProjectDTO> expected = projectsDTOOne;

        //Act
        List<ProjectDTO> result = listProjectsInAccountController.listProjectsByAccount("mike@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListProjectByAccountIsEmpty_NoPermission() {
        //Arrange
        List<ProjectDTO> expected = new ArrayList<>();
        accountFour.setProfile(profileOne);

        //Act
        List<ProjectDTO> result = listProjectsInAccountController.listProjectsByAccount("mary@isep.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }
}