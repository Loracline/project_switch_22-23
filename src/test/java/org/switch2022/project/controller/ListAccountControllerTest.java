package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;
import org.switch2022.project.dto.AccountEmailStatusDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BeforeEach and AfterEach executes common code before/after running the tests below.
 */

class ListAccountControllerTest {
    Account accountOne, accountTwo;

    AccountEmailStatusDTO accountEmailStatusDTOOne, accountEmailStatusDTOTwo;
    Profile profileOne, profileTwo;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSectorContainer businessSectorContainer;
    Project project;
    ProjectContainer projectContainer;
    Company company;
    Customer customerOne, customerTwo;
    CustomerContainer customerContainer;
    ProjectTypologyContainer projectTypologyContainer;

    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    LocalDate endDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;

    List<AccountEmailStatusDTO> accountEmailStatusDTOList;
    AccountInProjectContainer accountInProjectContainer;

    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);

        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Paul", "paul@isep.ipp.pt", 939855689, null);

        accountEmailStatusDTOOne = new AccountEmailStatusDTO("mike@isep.ipp.pt", true);
        accountEmailStatusDTOTwo = new AccountEmailStatusDTO("paul@isep.ipp.pt", true);

        accountEmailStatusDTOList = new ArrayList<>();
        accountEmailStatusDTOList.add(accountEmailStatusDTOOne);
        accountEmailStatusDTOList.add(accountEmailStatusDTOTwo);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("User");

        accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);
        accountInProject2 = new AccountInProject(accountTwo, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
    }


    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        profileTwo = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        project = null;
        projectContainer = null;
        customerOne = null;
        customerTwo = null;
        customerContainer = null;
        company = null;
    }

    @Test
    void ensureAllAccountsAreListedSuccessfully() {
        List<AccountEmailStatusDTO> expected = accountEmailStatusDTOList;
        ListAccountController newListAccountController = new ListAccountController(company);

        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator

        // Act
        List<AccountEmailStatusDTO> result = newListAccountController.listAllAccounts(emailActor);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAllAccountsAreListedUnsuccessfully_ProfileNotAuthorized() {
        List<AccountEmailStatusDTO> expected = new ArrayList<>();
        ListAccountController newListAccountController = new ListAccountController(company);

        // Act
        List<AccountEmailStatusDTO> result = newListAccountController.listAllAccounts("mike@isep.ipp.pt");

        // Assert
        assertEquals(expected, result);
    }
}