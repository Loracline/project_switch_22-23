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
        customerOne = new Customer("ISEP");
        customerTwo = new Customer("PortoTech");

        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);
        customers.add(customerOne);
        customers.add(customerTwo);

        //projects
        projectOne = new Project("proj001",
                "software development management", customerOne,
                projectTypologyOne, businessSector);
        projectTwo = new Project("proj002", "project software",
                customerTwo, projectTypologyTwo, businessSector);

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
    void ensureProductOwnerIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManager = "jane@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";
        accountDTO.phoneNumber = 912345678;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - product owner
        AllocationDTO allocationDTOPO = new AllocationDTO();
        allocationDTOPO.accountDTO = accountDTO;
        allocationDTOPO.projectDTO = projectDTO;
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);
        allocationDTOPO.endDate = LocalDate.of(2023, 1, 22);
        AddUserToProjectController controller = new AddUserToProjectController(company);

        //Act
        boolean result = controller.addUserToProject(emailManager, allocationDTOPO);

        //Assert
        assertTrue(result);

    }

    /**
     * US011 - As Manager, I want to associate a user as Team Member of a project.
     */
    @Test
    void ensureTeamMemberIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManager = "jane@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "Anna";
        accountDTO.email = "anna@isep.ipp.pt";
        accountDTO.phoneNumber = 912345688;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - team member
        AllocationDTO allocationDTOTM = new AllocationDTO();
        allocationDTOTM.accountDTO = accountDTO;
        allocationDTOTM.projectDTO = projectDTO;
        allocationDTOTM.role = "Team Member";
        allocationDTOTM.costPerHour = 7.5f;
        allocationDTOTM.percentageAllocation = 5.0f;
        allocationDTOTM.startDate = LocalDate.of(2023, 1, 19);
        allocationDTOTM.endDate = LocalDate.of(2023, 1, 22);
        AddUserToProjectController controller = new AddUserToProjectController(company);

        //Act
        boolean result = controller.addUserToProject(emailManager, allocationDTOTM);

        //Assert
        assertTrue(result);
    }

    /**
     * US013 - As Manager, I want to define the SM of a project.
     */
    @Test
    void ensureScrumMasterIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManager = "jane@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "Mary";
        accountDTO.email = "mary@isep.ipp.pt";
        accountDTO.phoneNumber = 852456951;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - scrum master
        AllocationDTO allocationDTOSM = new AllocationDTO();
        allocationDTOSM.accountDTO = accountDTO;
        allocationDTOSM.projectDTO = projectDTO;
        allocationDTOSM.role = "Scrum Master";
        allocationDTOSM.costPerHour = 7.5f;
        allocationDTOSM.percentageAllocation = 45.0f;
        allocationDTOSM.startDate = LocalDate.of(2023, 1, 19);
        allocationDTOSM.endDate = LocalDate.of(2023, 1, 22);
        AddUserToProjectController controller = new AddUserToProjectController(company);

        //Act
        boolean result = controller.addUserToProject(emailManager, allocationDTOSM);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureAllocationFailsBecauseRoleIsNotValid() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManager = "jane@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "Peter";
        accountDTO.email = "peter@isep.ipp.pt";
        accountDTO.phoneNumber = 564897231;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - scrum master
        AllocationDTO allocationDTOSM = new AllocationDTO();
        allocationDTOSM.accountDTO = accountDTO;
        allocationDTOSM.projectDTO = projectDTO;
        allocationDTOSM.role = "Administrator";
        allocationDTOSM.costPerHour = 7.5f;
        allocationDTOSM.percentageAllocation = 45.0f;
        allocationDTOSM.startDate = LocalDate.of(2023, 1, 19);
        allocationDTOSM.endDate = LocalDate.of(2023, 1, 22);
        AddUserToProjectController controller = new AddUserToProjectController(company);

        //Act
        boolean result = controller.addUserToProject(emailManager, allocationDTOSM);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureAllocationFailsBecauseProfileIsNotManager() {
        //Arrange
        //email manager
        accountThree.setProfile(profileThree);
        String emailManagerInvalid = "emma@isep.ipp.pt";
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "Peter";
        accountDTO.email = "peter@isep.ipp.pt";
        accountDTO.phoneNumber = 564897231;
        accountDTO.photo = null;
        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customerOne,
                projectTypologyOne
                , businessSector);

        projectDTO.name = "Test";
        projectDTO.status = "planned";
        projectDTO.businessSector = new BusinessSector("IT Sector");
        //account in project dto - scrum master
        AllocationDTO allocationDTOSM = new AllocationDTO();
        allocationDTOSM.accountDTO = accountDTO;
        allocationDTOSM.projectDTO = projectDTO;
        allocationDTOSM.role = "Product Owner";
        allocationDTOSM.costPerHour = 7.5f;
        allocationDTOSM.percentageAllocation = 45.0f;
        allocationDTOSM.startDate = LocalDate.of(2023, 1, 19);
        allocationDTOSM.endDate = LocalDate.of(2023, 1, 22);
        AddUserToProjectController controller = new AddUserToProjectController(company);

        //Act
        boolean result = controller.addUserToProject(emailManagerInvalid,
                allocationDTOSM);

        //Assert
        assertFalse(result);
    }
}