package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.AccountContainer;
import org.switch2022.project.model.container.BusinessSectorContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddBusinessSectorControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

  Account accountOne;
  Profile profileOne, profileTwo;
  List<Account> accounts;
  AccountContainer accountContainer;
  BusinessSector businessSectorOne;
  BusinessSectorContainer businessSectorContainer;
  Project projectOne;
  Company company;
  ProjectTypologyContainer projectTypologyContainer;
  Customer customerOne, customerTwo;
  CustomerContainer customerContainer;
  List<Customer> customers;
  AddBusinessSectorController addBusinessSectorController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");

        //customer
        customerOne = new Customer("Genius Software", "234567890");

        //projectTypology
      projectTypologyContainer = new ProjectTypologyContainer();
      projectTypologyContainer.createProjectTypology("Fixed Cost");
      projectTypologyContainer.createProjectTypology("Fixed time and materials");

      //business sector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne,
                new ProjectTypology("Fixed Cost"), businessSectorOne);

        //containers
        accountContainer = new AccountContainer(accounts);
        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");

    projectTypologyContainer = new ProjectTypologyContainer();
    projectTypologyContainer.createProjectTypology("Fixed Cost");
    projectTypologyContainer.createProjectTypology("Fixed time and materials");

    customerOne = new Customer("ISEP", "222333444");
    customerTwo = new Customer("PortoTech", "222333445");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);
    customers.add(customerOne);
    customers.add(customerTwo);
        //company
        company = new Company(accountContainer, null, businessSectorContainer,
                null, null, null, null);

        //controller
        addBusinessSectorController = new AddBusinessSectorController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        profileTwo = null;
        customerOne = null;
        projectOne = null;
        accounts.clear();
        businessSectorOne = null;
        accountContainer = null;
        businessSectorContainer = null;
        projectTypologyContainer = null;
        company = null;
        addBusinessSectorController = null;
    }

    /**
     * US008
     * Tests if the addition of a business sector is performed successfully if the actor
     * is an administrator.
     * Expected return: true
     */
    @Test
    void ensureThatNewBusinessSectorIsAddedSuccessfullyIfActorIsAdministrator() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = true;
        //Act
        boolean result = addBusinessSectorController.addBusinessSector("mining",
                emailActor);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * US008
     * Tests if the addition of a business sector is not performed if the
     * name of the business sector is invalid (empty or already existent).
     * Expected return = false
     */

    @Test
    void ensureThatNewBusinessSectorIsNotAddedIfNameIsInvalid() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        //Act
        //business sector "fishing" already exists
        boolean result = addBusinessSectorController.addBusinessSector("fishing",
                emailActor);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * US008
     * Tests if the addition of a business sector is not performed if the
     * actor is not an administrator.
     * Expected return = false
     */
    @Test
    void ensureThatNewBusinessSectorIsNotAddedIfActorIsNotAdministrator() {
        //Arrange
        //set profileOne (Manager) to accountOne
        accountOne.setProfile(profileTwo);
        String emailActor = accountOne.getEmail(); //Manager
        boolean expected = false;
        //Act
        //"paul@isep.ipp.pt" is not the administrator
        boolean result = addBusinessSectorController.addBusinessSector("mining",
                emailActor);
        //Assert
        assertEquals(expected, result);
    }
}