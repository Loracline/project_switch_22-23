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

    Account accountOne, accountTwo;
    AccountDTO accountDTOOne;
    Profile profileOne, profileTwo;
    Customer customerOne;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    AccountInProject accountInProjectOne;
    List<AccountDTO> accountsDTOOne;
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
        accountTwo = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);

        //accountDTO
        accountDTOOne = AccountMapper.accountToDTO(accountOne);
        accountsDTOOne = new ArrayList<>();
        accountsDTOOne.add(accountDTOOne);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");

        //customer
        customerOne = new Customer("Genius Software", "234567890");

        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne );


        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", 1,
                34f, LocalDate.of(2020, 1, 8));
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);

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
        company.changeProfile("mary@isep.ipp.pt", "Manager");
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
        accountsDTOOne.clear();
        accountsInProject.clear();
        accountContainer = null;
        profileContainer=null;
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
}