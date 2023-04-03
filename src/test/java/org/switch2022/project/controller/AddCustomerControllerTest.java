package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.Company;
import org.switch2022.project.container.CustomerContainer;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.factories.FactoryCustomer;
import org.switch2022.project.factories.IFactoryCustomer;
import org.switch2022.project.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    CustomerContainer customerContainer;
    Company company;
    AddCustomerController addCustomerController;
    IFactoryCustomer factoryCustomer;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

        //customer
        customerOne = new Customer("Genius Software", "234567890");


        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", new Customer("Genius Software", "234567890"),
                projectTypologyOne, businessSectorOne);

        //factory
        factoryCustomer = new FactoryCustomer();

        //containers
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");
        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("Genius Software", "234567890", factoryCustomer);


        //company
        company = new Company(accountContainer, profileContainer, null,
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
        accountContainer = null;
        customerContainer = null;
        profileContainer = null;
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
    void ensureThatNewCustomerIsSuccessfullyAddedIfActorIsAdministrator() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = true;
        //Act
        boolean result = addCustomerController.addCustomer("Critical", "233444000",
                emailActor, factoryCustomer);
        //Assert
        assertEquals(expected, result);
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
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        // Act
        boolean result = addCustomerController.addCustomer("", "234345456", emailActor,
                factoryCustomer);
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
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        // Act
        boolean result = addCustomerController.addCustomer("Critical", "", emailActor,
                factoryCustomer);
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
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        // Act
        boolean result = addCustomerController.addCustomer("Critical", "2223334444",
                emailActor, factoryCustomer);
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
        company.changeProfile("mike@isep.ipp.pt", "Manager");
        String emailActor = accountOne.getEmail(); //Manager
        boolean expected = false;
        //Act
        boolean result = addCustomerController.addCustomer("Critical", "255666777",
                emailActor, factoryCustomer);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests done with isolation using mockito framework.
     */

    /**
     * Tests if the addition of a new customer is performed successfully if the actor is an administrator.
     * Expected return: true
     */

    @Test
    void ensureThatNewCustomerIsSuccessfullyAddedIfActorIsAdministratorWithIsolation() {
        //Arrange
        Company companyDouble = mock(Company.class);
        AddCustomerController addCustomerControllerDouble = new AddCustomerController(companyDouble);
        when(companyDouble.validateProfileRequired(any(), any())).thenReturn(true);
        when(companyDouble.addCustomer(any(), any(), any())).thenReturn(true);

        //Act
        boolean result = addCustomerControllerDouble.addCustomer("Critical", "233444000",
                "mike@isep.ipp.pt", factoryCustomer);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests if the addition of a new customer is not performed.
     * Expected return: false
     */
    @Test
    void ensureThatNewCustomerIsNotAddedWithIsolation() {
        // Arrange
        Company companyDouble = mock(Company.class);
        AddCustomerController addCustomerControllerDouble = new AddCustomerController(companyDouble);
        when(companyDouble.validateProfileRequired(any(), any())).thenReturn(true);
        when(companyDouble.addCustomer(any(), any(), any())).thenReturn(false);

        // Act
        boolean result = addCustomerControllerDouble.addCustomer("Mike", "234345456",
                "mike@isep.ipp.pt", factoryCustomer);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the addition of a new customer is not performed if the actor is not an administrator.
     * Expected return: false
     */

    @Test
    void ensureAddCustomerIsNotSuccessfulInvalidProfileWithIsolation() {
        //Arrange
        Company companyDouble = mock(Company.class);
        AddCustomerController addCustomerControllerDouble = new AddCustomerController(companyDouble);
        when(companyDouble.validateProfileRequired(any(), any())).thenReturn(false);
        when(companyDouble.addCustomer(any(), any(), any())).thenReturn(true);

        // Act
        boolean result = addCustomerControllerDouble.addCustomer("Mike", "234345456",
                "mike@isep.ipp.pt", factoryCustomer);

        // Assert
        assertFalse(result);
    }
}