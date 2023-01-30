package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.model.*;

import static org.junit.jupiter.api.Assertions.*;


class CreateProfileControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Profile profileOne, profileTwo;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    BusinessSectorContainer businessSectorContainer;
    CustomerContainer customerContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    CreateProfileController createProfileController;


    @BeforeEach
    void setUp() {

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");

        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("User");

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        createProfileController = new CreateProfileController(company);
    }

    @AfterEach
    void tearDown() {
        profileOne = null;
        profileTwo = null;
        profileContainer = null;
        company = null;
        createProfileController = null;
    }

    @Test
    void addNewProfileSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = createProfileController.createProfile("Manager");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void addNewProfileUnsuccessfullyInvalidName() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = createProfileController.createProfile("User");
        //Assert
        assertEquals(expected, result);
    }
}