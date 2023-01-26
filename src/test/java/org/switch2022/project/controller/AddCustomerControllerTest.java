package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.model.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddCustomerControllerTest {
   /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne;
    Profile profileOne, profileTwo;
    Customer customerOne;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    List<Account> accounts;
    AccountContainer accountContainer;
    CustomerContainer customerContainer;
    Company company;
    AddCustomerController addCustomerController;

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
        projectTypologyOne = new ProjectTypology("Fixed Cost");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software",new Customer ("Genius Software", "234567890"),
                projectTypologyOne, businessSectorOne);

        //containers
        accountContainer = new AccountContainer(accounts);
        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("Genius Software","234567890");

        //company
        company = new Company(accountContainer, null, null,
                null, null, null, customerContainer);

        //controller
        addCustomerController = new AddCustomerController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        profileTwo = null;
        customerOne = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        projectOne = null;
        accounts.clear();
        accountContainer = null;
        customerContainer = null;
        company = null;
        addCustomerController = null;
    }

    /**
     * US009
     * Tests if the addition of a new customer is performed successfully if the actor
     * is an administrator.
     * Expected return: true
     */

    @Test
    void ensureThatNewCustomerIsSuccessfullyAddedIfActorIsAdministrator(){
        //Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = true;
        //Act
        boolean result = addCustomerController.addCustomer("Critical","233444000", emailActor);
        //Assert
        assertEquals(expected,result);
    }

    /**
     * US009
     * Tests if the addition of a new customer is not performed if the
     * name of the customer is empty.
     * Expected return = false
     */
    @Test
    void ensureThatNewCustomerIsNotAddedIfNameIsEmpty() {
        // Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        // Act
        boolean result = addCustomerController.addCustomer("", "234345456", emailActor);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * US009
     * Tests if the addition of a new customer is not performed if the
     * NIF of the customer is empty.
     * Expected return = false
     */

    @Test
    void ensureThatNewCustomerIsNotAddedIfNIFIsEmpty() {
        // Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        // Act
        boolean result = addCustomerController.addCustomer("Critical", "", emailActor);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * US009
     * Tests if the addition of a new customer is not performed if the
     * NIF of the customer is invalid (no. digits != 9).
     * Expected return = false
     */
    @Test
    void ensureThatNewCustomerIsNotAddedIfNIFIsInvalid() {
        // Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        // Act
        boolean result = addCustomerController.addCustomer("Critical", "2223334444", emailActor);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * US009
     * Tests if the addition of a new customer is not performed if the actor is not an
     * administrator.
     * Expected return = false
     */

    @Test
    void addCustomerUnsuccessfullyInvalidProfile() {
        //Arrange
        //set profileTwo (Manager) to accountOne
        accountOne.setProfile(profileTwo);
        String emailActor = accountOne.getEmail(); //Manager
        boolean expected = false;
        //Act
        boolean result = addCustomerController.addCustomer("Critical", "255666777", emailActor);
        // Assert
        assertEquals(expected, result);
    }

}