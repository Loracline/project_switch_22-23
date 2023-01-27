package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.controller.RegisterProjectController;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ProjectDTO;

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
  Project projectOne, projectTwo;
  ProjectDTO projectOneDTO, projectTwoDTO, projectThreeDTO;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  Company company;
  RegisterProjectController registerProjectController;


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

    projectOne = new Project("AA001", "Aptoide", new Customer("ISEP","228674498"),
            new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
    projectTwo = new Project("AA002", "Aptoide", new Customer("PortoTech","228674498"),
            new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer();

    projectOneDTO = new ProjectDTO("AA001", "Aptoide", "ISEP", "Fixed cost",
            "fishing");
    projectTwoDTO = new ProjectDTO("AA004", "Aptoide", "PortoTech", "Fixed cost",
            "Hunting");
    projectThreeDTO = new ProjectDTO("AA001", "Aptoide", "ISEP", "Fixed cost",
            "fishing");

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, null, customerContainer);

    registerProjectController = new RegisterProjectController(company);

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
    businessSectorContainer = null;
    projectTypologyContainer = null;
    projects.clear();
    projectContainer = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    customerContainer = null;
    company = null;
  }
//todo test
}
