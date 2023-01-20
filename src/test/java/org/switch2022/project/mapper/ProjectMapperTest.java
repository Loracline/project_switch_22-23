/*
package org.switch2022.project.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

class ProjectMapperTest {

  */
/**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   *//*


  Account accountOne, accountTwo, accountThree;
  Profile profileOne, profileTwo, profileThree;
  ProjectTypology projectTypologyOne, projectTypologyTwo;
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
  Project project;
  ProjectContainer projectContainer;
  ProjectDTO projectOneDTO, projectTwoDTO;
  Company company;

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

    projects = new ArrayList<>();
    projectContainer = new ProjectContainer(projects);
    projects.add(project);

    projectTypologyOne = new ProjectTypology("Fixed Cost");
    projectTypologyTwo = new ProjectTypology("Fixed time and materials");
    typologies = new ArrayList<>();
    projectTypologyContainer = new ProjectTypologyContainer(typologies);
    typologies.add(projectTypologyOne);
    typologies.add(projectTypologyTwo);
    projectTypologyContainer = new ProjectTypologyContainer(typologies);

    customer = new Customer("ISEP");

    projectOne = new Project("AA001", "software development management", customer, projectTypologyOne,
            businessSector);
    projectTwo = new Project("AA002", "project software", customer, projectTypologyTwo, businessSector);
    projectThree = new Project("AA003", "motor software", customer, projectTypologyTwo, businessSector);

    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projects.add(projectThree);
    projectContainer = new ProjectContainer(projects);

    projectOneDTO = new ProjectDTO("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectTwoDTO = new ProjectDTO("AA004", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer);
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
    project = null;
    projects.clear();
    projectContainer = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    company = null;
  }

  @Test
  void createProjectDTO() {
  //ProjectDTO reference = new ProjectDTO();
  }

  @Test
  void createProject() {
  }
}*/
