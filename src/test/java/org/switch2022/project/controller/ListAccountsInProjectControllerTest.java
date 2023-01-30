package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListAccountsInProjectControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo;
    AccountDTO accountDTOOne;
    List<AccountDTO> accountsDTOOne;
    Project projectOne, projectTwo, projectThree;
    AccountInProject accountInProjectOne, accountInProjectTwo;
    List<AccountInProject> accountsInProject;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    ListAccountsInProjectController listAccountsInProjectController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);

        //accountDTO
        accountDTOOne = AccountMapper.accountToDTO(accountOne);
        accountsDTOOne = new ArrayList<>();
        accountsDTOOne.add(accountDTOOne);

        //project
        projectOne = new Project("1A", null, null, null, null);
        projectTwo = new Project("1B", null, null, null, null);
        projectThree = new Project("1C", null, null, null, null);

        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Scrum Master", 1, 34f, null);
        accountInProjectTwo = new AccountInProject(accountOne, projectTwo, "Product Owner", 1, 34f, null);
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);

        //containers
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Mary", "mary@isep.ipp.pt", 939855689, null);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        //company
        company = new Company(accountContainer, profileContainer, null,
                null, null, accountInProjectContainer, null);

        //controller
        listAccountsInProjectController = new ListAccountsInProjectController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountDTOOne = null;
        accountsDTOOne.clear();
        projectOne = null;
        projectTwo = null;
        projectThree = null;
        accountInProjectOne = null;
        accountInProjectTwo = null;
        accountsInProject.clear();
        accountContainer = null;
        profileContainer = null;
        accountInProjectContainer = null;
        company = null;
        listAccountsInProjectController = null;
    }

    /**
     * US014
     * Test for listing all resources allocated to a given project.
     */
    @Test
    void ensureAllAccountsInAProjectAreListedSuccessfully() {
        //Arrange
        List<AccountDTO> expected = accountsDTOOne;
        company.changeProfile("mary@isep.ipp.pt", "Manager");

        //Act
        List<AccountDTO> result = listAccountsInProjectController.listAccountsByProject("mary@isep.ipp.pt", "1A");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to assure that no user other than Manager can access the list of resources of a project.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedForAnUnauthorizedAccount() {
        //Arrange
        List<AccountDTO> expected = new ArrayList<>();
        company.changeProfile("mary@isep.ipp.pt", "Administrator");

        //Act
        List<AccountDTO> result = listAccountsInProjectController.listAccountsByProject("mary@isep.ipp.pt", "1A");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to assure that is returned an empty list when a project has no one assigned to it yet.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedWhenAProjectHasNoOneAssignedToIt() {
        //Arrange
        List<AccountDTO> expected = new ArrayList<>();
        company.changeProfile("mary@isep.ipp.pt", "Manager");

        //Act
        List<AccountDTO> result = listAccountsInProjectController.listAccountsByProject("mary@isep.ipp.pt", "1C");

        //Assert
        assertEquals(expected, result);
    }
}