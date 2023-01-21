package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.controller.RegisterProjectController;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.mapper.ProjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectMapperTest {


  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne, accountTwo, accountThree;
  Profile profileOne, profileTwo, profileThree;
  float costPerHour;
  float percentageAllocation;
  LocalDate startDate;
  ProjectTypology projectTypologyOne, projectTypologyTwo, projectTypology;
  Project projectOne, projectTwo, projectThree;
  Customer customer;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  List<ProjectTypology> typologies;
  BusinessSector businessSector;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  ProjectDTO projectOneDTO, projectTwoDTO;

  AccountInProject accountInProject;

  List<AccountInProject> accountsInProject;

  AccountInProjectContainer accountInProjectContainer;
  Company company;
  RegisterProjectController registerProjectController;
  Customer customerOne, customerTwo;
  CustomerContainer customerContainer;
  List<Customer> customers;

  ProjectMapper projectMapper;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
    accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);
    accounts.add(accountTwo);
    accounts.add(accountThree);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");
    profileThree = new Profile("Manager");
    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    businessSector = new BusinessSector("fishing");
    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    projectTypologyOne = new ProjectTypology("Fixed Cost");
    projectTypologyTwo = new ProjectTypology("Fixed time and materials");
    typologies = new ArrayList<>();
    projectTypologyContainer = new ProjectTypologyContainer(typologies);
    typologies.add(projectTypologyOne);
    typologies.add(projectTypologyTwo);
    projectTypologyContainer = new ProjectTypologyContainer(typologies);
    projectTypology = new ProjectTypology("fixed cost");

    costPerHour = 7.5f;
    percentageAllocation = 45.0f;
    startDate = LocalDate.of(2023, 01, 19);
    accountInProject = new AccountInProject(accountOne, projectOne, "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    customerOne = new Customer("ISEP");
    customerTwo = new Customer("PortoTech");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);
    customers.add(customerOne);
    customers.add(customerTwo);


    projectOne = new Project("AA001", "Aptoide", customerOne, projectTypologyOne,
            businessSector);
    projectTwo = new Project("AA002", "project software", customerOne, projectTypologyTwo, businessSector);
    projectThree = new Project("AA003", "motor software", customerOne, projectTypologyTwo, businessSector);
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer(projects);

    projectOneDTO = new ProjectDTO("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("fishing"));
    projectTwoDTO = new ProjectDTO("AA004", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

    registerProjectController = new RegisterProjectController(company);

    projectMapper = new ProjectMapper();

  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    accountThree = null;
    profileOne = null;
    profileTwo = null;
    profileThree = null;
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSector = null;
    businessSectors.clear();
    businessSectorContainer = null;
    projectTypologyOne = null;
    projectTypologyTwo = null;
    typologies.clear();
    projectTypologyContainer = null;
    projects.clear();
    projectContainer = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    customerOne = null;
    customerTwo = null;
    customers.clear();
    customerContainer = null;
    company = null;
  }
/*
  @Test
  void creationOfProjectDTOSuccessful() {
    ProjectDTO reference = projectMapper.toDTO(projectOne);
    boolean expected = true;
    boolean result = reference.equals(projectOneDTO);
    assertEquals(expected,result);
  }*/

  @Test
  void creationOfProjectDTONotSuccessful() {
    ProjectDTO reference = projectMapper.toDTO(projectTwo);
    boolean expected = false;
    boolean result = reference.equals(projectOneDTO);
    assertEquals(expected,result);
  }
/*
  @Test
  void creationOfProjectSuccessful() {
    Project reference = projectMapper.fromDTO(projectOneDTO);
    boolean expected = true;
    boolean result = reference.equals(projectOne);
    assertEquals(expected,result);
  }*/

  @Test
  void creationOfProjectNotSuccessful() {
    Project reference = projectMapper.fromDTO(projectTwoDTO);
    boolean expected = false;
    boolean result = reference.equals(projectOne);
    assertEquals(expected,result);
  }
}
